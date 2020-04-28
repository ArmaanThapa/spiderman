package com.xworkzcm.main.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdateDTO {
	
	@NotEmpty(message="Required field")
	private String email;
	
	private String ipAddress;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "UpdateDTO [email=" + email + ", ipAddress=" + ipAddress + "]";
	}

	
	
}
