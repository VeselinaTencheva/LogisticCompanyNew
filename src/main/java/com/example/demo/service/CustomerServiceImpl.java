package com.example.demo.service;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, RoleService roleService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.customerRepository = customerRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
	public Customer registerCustomer(Customer customer) {
		this.roleService.seedRolesInDb();
		if (this.customerRepository.count() == 0) {
			customer.setAuthorities(this.roleService.findAllRoles());
		} else {
			customer.setAuthorities(new ArrayList<>());
			customer.getAuthorities().add(this.roleService.findByAuthority("ROLE_CLIENT"));
		}
		customer.setPassword(this.bCryptPasswordEncoder.encode(customer.getPassword()));

		return this.customerRepository.saveAndFlush(customer);
	}

}
