package com.example.demo.service;


import com.example.demo.entities.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    List<Role> findAllRoles();

    Role findByAuthority(String authority);

    Role findById(String id);
}
