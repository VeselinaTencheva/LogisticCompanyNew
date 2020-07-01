package com.example.demo.repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByUsername(String username);

    Set<Employee> findEmployeesByOffice(Office office);
}
