package com.example.demo.controller;

import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController   {

    private final CustomerService customerService;

    private final ShipmentService shipmentService;

    private final UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, ShipmentService shipmentService, UserService userService) {
        this.customerService = customerService;
        this.shipmentService = shipmentService;
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String register() {
        return "customer/register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerConfirm(@ModelAttribute Customer customer) {
        this.customerService.registerCustomer(customer);

        return "login";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public String all(Model model) {
        model.addAttribute("customers",customerService.findAllCustomers());
        return "customer/all";
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ResponseBody
    public List<Customer> fetchCustomers(Model model) {
        List<Customer> customers = this.customerService.findAllCustomers();
        return customers;

    }

    @GetMapping("/sentShipments")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String sentShipmentsByCustomer(Principal principal,Model model) {
        Customer customer = customerService.findCustomerById(userService.findUserByUserName(principal.getName()).getId());
        model.addAttribute("shipments",shipmentService.findSentShipmentsByCustomer(customer));
        return "shipment/all";
    }

    @GetMapping("/receivedShipments")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public String receivedShipmentsByCustomer(Principal principal,Model model) {
        Customer customer = customerService.findCustomerById(userService.findUserByUserName(principal.getName()).getId());
        model.addAttribute("shipments",shipmentService.findReceivedShipmentsByCustomer(customer));
        return "shipment/all";
    }



}
