package com.xworkzcm.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User_Table")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "dev", strategy = "increment")
	@GeneratedValue(generator = "dev")
	@Column(name = "Id")
	int id;

	@Column(name = "User_Id")
	private String userId;

	@Column
	private String password;

	@Column(name = "User_Email")
	private String email;

	@Column(name = "Phone_Number")
	private String phoneNumber;

	@Column(name = "User_Course")
	private String courses;

	@Column(name = "User_NoOfAttemp")
	private int noOfAttemp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoOfAttemp() {
		return noOfAttemp;
	}

	public void setNoOfAttemp(int noOfAttemp) {
		this.noOfAttemp = noOfAttemp;
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

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", courses=" + courses + ", noOfAttemp=" + noOfAttemp + "]";
	}

}
