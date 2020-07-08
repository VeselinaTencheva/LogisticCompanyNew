package com.example.demo.service;


import com.example.demo.entities.Employee;
import com.example.demo.entities.Office;
import com.example.demo.entities.Shipment;
import com.example.demo.models.binding.EmployeeDTO;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

	public List<Employee> findAllEmployees();

	public Employee createEmployee(Employee employee);

	public Employee updateEmployee(EmployeeDTO employee);

	public void registerShipment(Employee employee, Shipment shipment);

	public void deliverShipment(Employee employee, Shipment shipment);

	Employee registerEmployee(EmployeeDTO employee);

	Employee findEmployeeByUserName(String name);

	Set<Employee> findEmployeesByOffice(Office office);

//	Employee editEmployeeProfile(Employee employeeServiceModel, String oldPassword);

//	void setEmployeeRole(String id, String role);

	Employee findEmployeeById(String id);



	void deleteEmployee(String employeeId);
}
