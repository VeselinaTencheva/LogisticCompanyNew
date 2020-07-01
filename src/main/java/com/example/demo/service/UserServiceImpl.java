package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}


	@Override
	public User findUserByUserName(String username) {
		return this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}


	//TODO vinagi hvurlq exception
	@Override
	public User editUserProfile(User user, String oldPassword) {
		if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IllegalArgumentException("Incorrect password");
		}
		user.setPassword(!"".equals(user.getPassword())
				? this.bCryptPasswordEncoder.encode(user.getPassword())
				: user.getPassword());
		user.setName(user.getName());
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}


	@Override
	public User findUserById(String id) {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public void deleteUser(String userId) {

		this.userRepository.delete(findUserById(userId));
	}

}
