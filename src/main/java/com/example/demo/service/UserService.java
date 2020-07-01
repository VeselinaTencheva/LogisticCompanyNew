package com.example.demo.service;

import com.example.demo.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findUserByUserName(String name);

	User editUserProfile(User user, String oldPassword);

	List<User> findAllUsers();


	User findUserById(String id);

	void deleteUser(String    userId);
}
