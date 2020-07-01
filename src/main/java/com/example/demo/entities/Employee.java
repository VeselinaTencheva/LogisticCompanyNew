package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends User {

	private EmployeeType employeeType;

	private List<Shipment> shipments;

	private Office office;

	public Employee() {

	}


	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	@JoinColumn(name="office_id")
	@ManyToOne
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "employee_type", nullable = false, unique = false, updatable = true)
	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	@Override
	public String toString() {
		return super.getName();
	}
}