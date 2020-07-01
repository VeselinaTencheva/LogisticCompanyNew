package com.example.demo.service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Shipment;
import com.example.demo.mapper.ShipmentMapper;
import com.example.demo.models.binding.ShipmentDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.util.CalculateShipmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	@Autowired
	private ShipmentRepository shipmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private ShipmentMapper shipmentMapper;

	@Override
	public List<Shipment> findAllShipments() {
		return shipmentRepository.findAll();
	}

	@Override
	public Shipment findShipmentById(String id) {
		return shipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public Shipment createShipment(ShipmentDTO shipmentDTO) {

		Shipment shipment=  this.shipmentMapper.mapShipmentDTOToShipment(shipmentDTO);
		shipment.setPrice(CalculateShipmentUtil.calculateShipmentPrice(shipment,officeRepository.findAll()));
		shipmentRepository.saveAndFlush(shipment);
		return shipment;
	}

	@Override
	public Shipment updateShipment(Shipment shipment) {
		return shipmentRepository.saveAndFlush(shipment);
	}

	@Override
	public void deleteShipment(String id) {
		Shipment shipment = shipmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		shipmentRepository.delete(shipment);
	}

	@Override
	public List<Shipment> getRegisteredShipments() {
		List<Shipment> registered = new ArrayList<>();
		List<Employee> employees = this.employeeRepository.findAll();
		for (Employee employee : employees) {
			registered.addAll(employee.getShipments());
		}
		return registered;
	}

	@Override
	public List<Shipment> findCheckedShipments() {
		return shipmentRepository.findShipmentByStatus(true);
	}

	@Override
	public List<Shipment> findUncheckedShipments() {
		return shipmentRepository.findShipmentByStatus(false);
	}

	@Override
	public List<Shipment> findDeliveredShipments() {
		return shipmentRepository.findShipmentByDelivered(true);
	}


	@Override
	public List<Shipment> findUndeliveredShipmentsByCustomer(Customer customer,boolean isDelivered) {
		return shipmentRepository.findShipmentByRecipientAndDelivered(customer,false);
	}

	@Override
	public List<Shipment> findDeliveredShipmentsByCustomer(Customer customer, boolean isDelivered) {
		return  shipmentRepository.findShipmentByRecipientAndDelivered(customer,true);
	}

	@Override
	public List<Shipment> findUndeliveredCheckedShipments() {
		return shipmentRepository.findShipmentByDeliveredAndStatus(false,true);
	}

	@Override
	public List<Shipment> findSentShipmentsByCustomer(Customer customer) {
		return shipmentRepository.findShipmentBySender(customer);
	}


	@Override
	public List<Shipment> findRegisteredShipmentsByEmployee(Employee employee) {
		return shipmentRepository.findShipmentByEmployee(employee);
	}
}
