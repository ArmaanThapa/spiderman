package com.xworkzcm.main.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User_Login")
public class UserLoginEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "div", strategy = "increment")
	@GeneratedValue(generator = "div")
	@Column(name = "Id")
	int id;

	@Column(name = "User_Email")
	private String email;

	@Column(name = "User_Password")
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
		return "UserLoginEntity [email=" + email + ", password=" + password + "]";
	}

}
