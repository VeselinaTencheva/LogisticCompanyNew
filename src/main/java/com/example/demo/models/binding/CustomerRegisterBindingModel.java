package com.example.demo.models.binding;

import com.example.demo.models.validations.ValidConfirmPassword;
import com.example.demo.models.validations.ValidPassword;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ValidConfirmPassword(first = "password", second = "confirmPassword", message = "- Error: The password fields must match!")
public class CustomerRegisterBindingModel {

	private String name;
	private String username;
	private String password;
	private String confirmPassword;

	public CustomerRegisterBindingModel() {
	}

	@NotBlank(message = " - Error: Name cannot be blank!")
	@Length(max = 32, min = 1,message = " - Error: Name length must be between 1 or 32 characters!")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank(message = " - Error: Username cannot be blank!")
	@Length(max = 32, min = 4,message = " - Error: Username length must be between 4 or 32 characters!")
	public String getUsername() {
		return username;
	}

	@Length(min = 6,max = 32,message = " - Error: Password must be between 6 and 32 characters!")
	public String getPassword() {
		return password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
