package com.example.demo.models.binding;

public class UserEditBindingModel {

	private String username;
	private String oldPassword;
	private String password;
	private String confirmPassword;
	
	
	public UserEditBindingModel() {
	}


	public String getUsername() {
		return username;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public String getPassword() {
		return password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	
}
