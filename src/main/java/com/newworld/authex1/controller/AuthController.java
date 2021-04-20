package com.newworld.authex1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newworld.authex1.exception.InternalException;
import com.newworld.authex1.util.Authentication;
import com.newworld.authex1.vo.AuthResp;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@GetMapping("/auth")
	public ResponseEntity fetchToken() throws InternalException {
		
		AuthResp resp = Authentication.encode();
		return new ResponseEntity(resp,HttpStatus.OK);
		
	}

}
