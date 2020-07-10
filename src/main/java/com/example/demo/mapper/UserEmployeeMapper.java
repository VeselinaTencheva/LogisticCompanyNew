package com.example.demo.mapper;

import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserEmployeeMapper {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
     RoleRepository roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User mapCustomerToUser(Customer customer) {
        User user = new User();
        user.setName(customer.getName());
        user.setUsername(customer.getUsername());
        user.setPassword(this.bCryptPasswordEncoder.encode(customer.getPassword()));
        user.setAuthorities(new ArrayList<>());
        user.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));


        return user;
    }
}
