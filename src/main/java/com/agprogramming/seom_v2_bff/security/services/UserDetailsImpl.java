package com.agprogramming.seom_v2_bff.security.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agprogramming.seom_v2_bff.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private String cuil;
	private LocalDate birthdate;
	private String email;
	@JsonIgnore
	private String password;
	private String stripeId;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String firstName, String lastName, String cuil, LocalDate birthdate, String email,
			String password, String stripeId, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cuil = cuil;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.stripeId = stripeId;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), user.getFirstName(), user.getLastName(), user.getCuil(),
				user.getBirthdate(), user.getEmail(), user.getPassword(), user.getStripeId(), authorities);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCuil() {
		return cuil;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getStripeId() {
		return stripeId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		UserDetailsImpl user = (UserDetailsImpl) o;

		return Objects.equals(id, user.id);
	}
}
