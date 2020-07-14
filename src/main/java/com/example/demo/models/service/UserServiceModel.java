package com.example.demo.models.service;

import java.util.Set;

public class UserServiceModel extends BaseServiceModel {
	
	private String username;
	private String password;
	
	private Set<RoleServiceModel> authorities;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public Set<RoleServiceModel> getAuthorities() {
		return authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(Set<RoleServiceModel> authorities) {
		this.authorities = authorities;
	} 
	
	

}
