package com.newworld.authex1.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthResp extends BaseResp {

	private String token;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonProperty("expiration")
	private LocalDateTime expireAt;
}