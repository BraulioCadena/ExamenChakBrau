package com.example.braulio.userapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.braulio.userapi.dto.LoginRequest;
import com.example.braulio.userapi.dto.LoginResponse;

@Service
public class AuthService {
	@Autowired
	private UserService userService;

	public LoginResponse authenticate(LoginRequest loginRequest) {

	}
}
