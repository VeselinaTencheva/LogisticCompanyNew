package com.example.demo.models.binding;

import com.example.demo.entities.EmployeeType;
import com.example.demo.entities.Role;
import com.example.demo.models.validations.ValidConfirmPassword;
import javafx.scene.Scene;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ValidConfirmPassword(first = "password", second = "confirmPassword", message = "- Error: The password fields must match!")
public class EmployeeDTO {

    private String officeId;
    private String username;
    private String password;
    private String name;
    private EmployeeType employeeType;
    private List<Role> authorities;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @NotBlank(message = " - Error: Username cannot be blank!")
    @Length(max = 32, min = 4,message = " - Error: Username length must be between 4 or 32 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 6,max = 32,message = " - Error: Password must be between 6 and 32 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = " - Error: Name cannot be blank!")
    @Length(max = 32, min = 1,message = " - Error: Name length must be between 1 or 32 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
}
