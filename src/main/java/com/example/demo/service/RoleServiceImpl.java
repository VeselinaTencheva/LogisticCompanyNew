package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        if(this.roleRepository.count()==0){
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_EMPLOYEE"));
            this.roleRepository.saveAndFlush(new Role("ROLE_CLIENT"));
            this.roleRepository.saveAndFlush(new Role("ROLE_DRIVER"));
        }
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
