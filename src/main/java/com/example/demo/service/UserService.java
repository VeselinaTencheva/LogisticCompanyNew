package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findUserByUserName(String name);


	UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

	List<User> findAllUsers();


	User findUserById(String id);

	void deleteUser(String    userId);

}
