package com.example.demo.mapper;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Shipment;
import com.example.demo.models.binding.ShipmentDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    public Shipment mapShipmentDTOToShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();

        shipment.setAddress(shipmentDTO.getAddress());
        shipment.setPrice(shipmentDTO.getPrice());
        shipment.setWeight(shipmentDTO.getWeight());
        shipment.setReceivedDate(null);

        Customer sender = customerService.findCustomerById(shipmentDTO.getSenderId());
        shipment.setSender(sender);

        Customer recipient = customerService.findCustomerById(shipmentDTO.getRecipientId());
        shipment.setRecipient(recipient);

        shipment.setEmployee(null);
        shipment.setStatus(false);
        shipment.setDelivered(false);
        shipment.setDriver(null);


        return shipment;
    }
}
