package com.agprogramming.seom_v2_bff.payloads.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, String refreshToken, Long id, String email, List<String> roles) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
}