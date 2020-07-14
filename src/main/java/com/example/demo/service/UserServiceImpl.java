package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.models.service.UserServiceModel;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService,
						   BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.modelMapper = modelMapper;
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



	@Override
	public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {
		User user = this.userRepository.findByUsername(userServiceModel.getUsername())
				.orElseThrow(()->new UsernameNotFoundException("Username not found"));

		if(!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IllegalArgumentException("Incorrect password");
		}

		user.setPassword(!"".equals(userServiceModel.getPassword())  ?
				this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :user.getPassword());
		return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
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
