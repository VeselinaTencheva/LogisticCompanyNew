package com.example.demo.service;

import com.example.demo.entities.Customer;
import com.example.demo.mapper.UserEmployeeMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final RoleService roleService;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserEmployeeMapper userEmployeeMapper;


	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, RoleService roleService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserEmployeeMapper userEmployeeMapper) {
		this.customerRepository = customerRepository;
		this.roleService = roleService;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userEmployeeMapper = userEmployeeMapper;
	}

	@Override
	public java.util.List<Customer> findAllCustomers() {

		return this.customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(String id) {
		return this.customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomerById(String id) {
		customerRepository.deleteById(id);
	}

	@Override
	public void registerCustomer(Customer customer) {
		this.roleService.seedRolesInDb();
		if (this.userRepository.count() == 0) {
			this.userRepository.saveAndFlush(userEmployeeMapper.mapCustomerToUser(customer));
		} else {
			customer.setAuthorities(new ArrayList<>());
			customer.getAuthorities().add(this.roleService.findByAuthority("ROLE_CLIENT"));
			customer.setPassword(this.bCryptPasswordEncoder.encode(customer.getPassword()));

			this.customerRepository.saveAndFlush(customer);
		}
	}

}
