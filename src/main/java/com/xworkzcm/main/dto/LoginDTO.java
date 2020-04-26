package com.xworkzcm.main.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginDTO {

	@NotEmpty(message = "invalid credentials ")
	private String email;

	@NotEmpty(message = "invalid credentials ")
	@Size(min = 8, max = 8, message = "invalid credentials ")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + "]";
	}

}
