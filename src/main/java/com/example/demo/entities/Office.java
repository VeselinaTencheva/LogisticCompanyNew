package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "offices")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Office extends BaseEntity {


	@NotBlank(message = " - Error: Address cannot be blank")
	private String address;

	private Company company;

	@Positive(message = " - Error: Shipping price cannot be negative")
	@NotNull(message = " - Error: Shipping price cannot be null")
	private  double shippingPrice;

	private Set<Employee> employees; 

	public Office() {
	}


	@Column(name = "office_address", nullable = false, unique = false, updatable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JoinColumn
	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Column(name = "price", nullable = false, unique = false, updatable = false)
	public double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Office other = (Office) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;
	}

}
