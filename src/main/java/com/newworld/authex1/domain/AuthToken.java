package com.newworld.authex1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthToken {

	private String token;

	private String module;

	private String company;

	private Long userId;

	public AuthToken(String module, String company, Long userId) {
		this.module = module;
		this.company = company;
		this.userId = userId;
	}

}