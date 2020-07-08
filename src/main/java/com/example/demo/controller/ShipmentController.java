package com.example.demo.controller;


import com.example.demo.entities.Employee;
import com.example.demo.entities.Shipment;
import com.example.demo.models.binding.ShipmentDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/shipments")
public class ShipmentController  {

	private final ShipmentService shipmentService;
	private final CustomerService customerService;
	private final UserServiceImpl userService;
	private final ModelMapper modelMapper;
	private final EmployeeService employeeService;


	@Autowired
	public ShipmentController(ShipmentService shipmentService, CustomerService customerService, UserServiceImpl userService, ModelMapper modelMapper, EmployeeService employeeService) {
		this.shipmentService = shipmentService;
//		this.modelMapper = modelMapper;
		this.customerService = customerService;
		this.userService = userService;
        this.modelMapper = modelMapper;
		this.employeeService = employeeService;
	}


	@GetMapping("/add")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public String register(Model model, @ModelAttribute(name="shipmentDTO") ShipmentDTO shipmentDTO) {
		model.addAttribute("shipment", shipmentDTO);
		model.addAttribute("customers",customerService.findAllCustomers());
		return "shipment/add";
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_EMPLOYEE')")
	public String registerConfirm(Principal principal,Model model, @ModelAttribute(name="shipmentDTO")ShipmentDTO shipmentDTO) {

		shipmentDTO.setSenderId(userService.findUserByUserName(principal.getName()).getId());
		Shipment shipment= this.shipmentService.createShipment(shipmentDTO);
		model.addAttribute("shipment", shipment);
		model.addAttribute("shipments",shipmentService.findAllShipments());
		return "shipment/all";

	}

//	@GetMapping("/shipments/edit/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public String edit(@PathVariable String id,Model model){
//		Shipment shipment = this.shipmentService.findShipmentById(id);
//		model.addAttribute("model", shipment);
//		model.addAttribute("customers",customerService.findAllCustomers());
//		return "shipment/edit";
//	}
//
//	@PostMapping("/edit/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public String editShipmentConfirm(@PathVariable String id,Model model, @ModelAttribute(name = "shipment") Shipment shipment) {
//
//		shipment.setId(id);
//		this.shipmentService.updateShipment(shipment);
//		model.addAttribute("shipments",shipmentService.findAllShipments());
//		return  "shipment/listed-shipments";
//	}


	@GetMapping("/allShipments")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public String allShipments(Model model) {
		model.addAttribute("shipments",shipmentService.findAllShipments());
		model.addAttribute("clients",customerService.findAllCustomers());
		return "shipment/all";
	}

	@GetMapping("/uncheckedShipments")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public String uncheckedShipments(Model model) {
		model.addAttribute("shipments",shipmentService.findUncheckedShipments());
		model.addAttribute("clients",customerService.findAllCustomers());
		return "shipment/all";
	}

	@GetMapping("/checkedShipments")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public String checkedShipments(Model model) {
		model.addAttribute("shipments",shipmentService.findCheckedShipments());
		model.addAttribute("clients",customerService.findAllCustomers());
		return "shipment/all";
	}

	@GetMapping("/undeliveredShipments")
	@PreAuthorize("hasAnyRole('ROLE_DRIVER','ROLE_ADMIN')")
	public String undeliveredShipments(Model model) {
		model.addAttribute("shipments",shipmentService.findUndeliveredCheckedShipments());
		model.addAttribute("clients",customerService.findAllCustomers());
		return "shipment/all";
	}

	@GetMapping("/deliveredShipments")
	@PreAuthorize("hasAnyRole('ROLE_DRIVER','ROLE_ADMIN')")
	public String deliveredShipments(Model model) {
		model.addAttribute("shipments",shipmentService.findDeliveredShipments());
		model.addAttribute("clients",customerService.findAllCustomers());
		return "shipment/all";
	}


	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	public String deleteShipment(@PathVariable String id, Model model) {
		Shipment shipment = this.shipmentService.findShipmentById(id);
		model.addAttribute("model", shipment);
		return "shipment/delete";
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	public String deleteShipmentConfirm(@PathVariable String id,Model model) {
		this.shipmentService.deleteShipment(id);
		model.addAttribute("shipments",shipmentService.findAllShipments());
		return "shipment/all";
	}


	@GetMapping("/shipments/registerShipment/{id}")
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	public String registerShipment(Principal principal, @PathVariable String id, Model model) {
		Shipment shipment = shipmentService.findShipmentById(id);
		Employee employee = this.employeeService.findEmployeeById(userService.findUserByUserName(principal.getName()).getId());
		employeeService.registerShipment(employee,shipment);
		model.addAttribute("model", shipmentService.findCheckedShipments());
		return "home";
	}

	@GetMapping("/shipments/deliverShipment/{id}")
	@PreAuthorize("hasAnyRole('ROLE_DRIVER','ROLE_ADMIN')")
	public String deliverShipment(Principal principal, @PathVariable String id, Model model) {
		Shipment shipment = shipmentService.findShipmentById(id);
		Employee employee = this.employeeService.findEmployeeById(userService.findUserByUserName(principal.getName()).getId());
		employeeService.deliverShipment(employee,shipment);
		model.addAttribute("model", shipmentService.findDeliveredShipments());
		return "shipment/all";
	}


}
