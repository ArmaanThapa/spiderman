package com.xworkzcm.main.entity;

import java.io.Serializable;

public class UpdateEntity implements Serializable {
	
	
	private String email;
	private String password;
	private String ipAddress;
	
	
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
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Override
	public String toString() {
		return "UpdateEntity [email=" + email + ", password=" + password + ", ipAddress=" + ipAddress + "]";
	}
	
	
	
	
  


}
