package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="shipments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shipment extends BaseEntity{
	private String address;
	private double weight;
	private double price;
	private Customer recipient;
	private Customer sender;
	private Employee employee;
	private Employee driver;
	private LocalDate receivedDate;
	private boolean status;
	private boolean isDelivered;
	
	public Shipment() {
	}

	@Column(name = "shipment_address", nullable = false, unique = false, updatable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "shipment_weight", nullable = false, unique = false, updatable = true)
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JoinColumn
	@ManyToOne
	public Customer getRecipient() {
		return recipient;
	}

	public void setRecipient(Customer recipient) {
		this.recipient = recipient;
	}

	@ManyToOne
	@JoinColumn
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JoinColumn
	@ManyToOne
	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	@Column(name = "date")
	public LocalDate getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Column(name = "status", nullable = true, unique = false, updatable = true)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	@Column(name = "delivered", nullable = true, unique = false, updatable = true)
	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean delivered) {
		isDelivered = delivered;
	}

	@ManyToOne
	@JoinColumn
	public Employee getDriver() {
		return driver;
	}

	public void setDriver(Employee driver) {
		this.driver = driver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Shipment other = (Shipment) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}
