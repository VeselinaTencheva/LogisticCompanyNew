package com.example.demo.mapper;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Office;
import com.example.demo.models.binding.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {


    @Autowired
    OfficeService officeService;

    @Autowired
    ModelMapper modelMapper;

    public Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeType(employeeDTO.getEmployeeType());
        employee.setName(employeeDTO.getName());
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());


        Office office = officeService.findById(employeeDTO.getOfficeId());
        employee.setOffice(office);

       employee.setAuthorities(employeeDTO.getAuthorities());


        return employee;
    }
}
