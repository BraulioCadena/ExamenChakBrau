package com.example.braulio.userapi.dto;

public class LoginResponse {
	private boolean success;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public LoginResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
}
