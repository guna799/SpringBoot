package com.springBoot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="log_in")
public class LogIn {
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="access")
	private String access;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public LogIn(String userName, String password, String access) {
		super();
		this.userName = userName;
		this.password = password;
		this.access = access;
	}

	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
