package com.xworkzcm.main.dto;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {

	@NotEmpty(message = "User Id is Must")
	private String userId;

	@NotEmpty(message = "Email Id is Must")
	private String email;

	@NotEmpty(message = "Phone Number is Must")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid Phone Number")
	private String phoneNumber;

	@NotEmpty(message = "Select one field")
	private String courses;

	private String agree;

	@Null(message = "You have select Disagree")
	private String disagree;

	private int noOfAttemp;

	public int getNoOfAttemp() {
		return noOfAttemp;
	}

	public void setNoOfAttemp(int noOfAttemp) {
		this.noOfAttemp = noOfAttemp;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getDisagree() {
		return disagree;
	}

	public void setDisagree(String disagree) {
		this.disagree = disagree;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", email=" + email + ", phoneNumber=" + phoneNumber + ", courses="
				+ courses + ", agree=" + agree + ", disagree=" + disagree + ", noOfAttemp=" + noOfAttemp + "]";
	}

}
