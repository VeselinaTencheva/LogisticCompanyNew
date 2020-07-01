package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "clients")
public class Customer extends User {


	private List<Shipment> sentShipments;
	private List<Shipment> receivedShipments;

	public Customer() {

	}

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	public List<Shipment> getSentShipments() {
		return sentShipments;
	}

	public void setSentShipments(List<Shipment> sentShipments) {
		this.sentShipments = sentShipments;
	}

	@OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
	public List<Shipment> getReceivedShipments() {
		return receivedShipments;
	}

	public void setReceivedShipments(List<Shipment> receivedShipments) {
		this.receivedShipments = receivedShipments;
	}

}
