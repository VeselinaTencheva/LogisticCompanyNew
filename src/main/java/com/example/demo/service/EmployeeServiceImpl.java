package com.example.demo.service;

import com.example.demo.entities.Employee;
import com.example.demo.entities.EmployeeType;
import com.example.demo.entities.Office;
import com.example.demo.entities.Shipment;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.models.binding.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeServiceImpl  implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ShipmentRepository shipmentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private  EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ShipmentRepository shipmentRepository, RoleService roleService,
                               BCryptPasswordEncoder bCryptPasswordEncoder, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.shipmentRepository = shipmentRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee registerEmployee(EmployeeDTO employeeDTO) {
        this.roleService.seedRolesInDb();
        employeeDTO.setAuthorities(new ArrayList<>());
        if(employeeDTO.getEmployeeType().equals(EmployeeType.OFFICE)){
            employeeDTO.getAuthorities().add(this.roleService.findByAuthority("ROLE_EMPLOYEE"));
        }
        else
        {
            employeeDTO.getAuthorities().add(this.roleService.findByAuthority("ROLE_DRIVER"));
        }
        employeeDTO.setPassword(this.bCryptPasswordEncoder.encode(employeeDTO.getPassword()));

        Employee employee=this.employeeMapper.mapEmployeeDTOToEmployee(employeeDTO);

        return this.employeeRepository.saveAndFlush(employee);

    }

    @Override
    public Employee findEmployeeByUserName(String username) {
        return this.employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Set<Employee> findEmployeesByOffice(Office office) {
        return employeeRepository.findEmployeesByOffice(office);
    }

//    @Override
//    public Employee editEmployeeProfile(Employee employee, String oldPassword) {
//
//        if (!this.bCryptPasswordEncoder.matches(oldPassword, employee.getPassword())) {
//            throw new IllegalArgumentException("Incorrect password");
//        }
//        employee.setPassword(!"".equals(employee.getPassword())
//                ? this.bCryptPasswordEncoder.encode(employee.getPassword())
//                : employee.getPassword());
//        employee.setName(employee.getName());
//        return employee;
//    }

    @Override
    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(String id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
    @Override
    public void deleteEmployee(String employeeId) {

        this.employeeRepository.delete(findEmployeeById(employeeId));
    }

	@Override
	public Employee createEmployee(Employee employee) {
		return this.employeeRepository.saveAndFlush(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {


		return this.employeeRepository.saveAndFlush(employee);
	}

    @Override
	public void registerShipment(Employee employee, Shipment shipment) {
        shipment.setStatus(true);
        shipment.setEmployee(employee);
        employee.getShipments().add(shipment);
       shipmentRepository.saveAndFlush(shipment);
	}

    @Override
    public void deliverShipment(Employee employee, Shipment shipment) {
        shipment.setDelivered(true);
        shipment.setDriver(employee);
        shipment.setReceivedDate(LocalDate.now());
        employee.getShipments().add(shipment);
        shipmentRepository.saveAndFlush(shipment);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return this.employeeRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//    }
}
