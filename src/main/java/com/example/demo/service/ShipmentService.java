package com.example.demo.service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Shipment;
import com.example.demo.models.binding.ShipmentDTO;

import java.util.List;

public interface ShipmentService {

	List<Shipment> findAllShipments();

	Shipment findShipmentById(String id);

	Shipment createShipment(ShipmentDTO shipment);

	Shipment updateShipment(Shipment shipment);

	void deleteShipment(String id);

	List<Shipment> getRegisteredShipments();

	List<Shipment> findCheckedShipments();

	List<Shipment> findUncheckedShipments();

	List<Shipment> findDeliveredShipments();


	List<Shipment> findUndeliveredShipmentsByCustomer(Customer customer,boolean isDelivered);

	List<Shipment> findDeliveredShipmentsByCustomer(Customer customer,boolean isDelivered);

	List<Shipment> findUndeliveredCheckedShipments();

	List<Shipment> findSentShipmentsByCustomer(Customer customer);


	List<Shipment> findRegisteredShipmentsByEmployee(Employee employee);
}
