package com.example.demo.controller;


import com.example.demo.entities.Employee;
import com.example.demo.models.binding.EmployeeDTO;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OfficeService;
import com.example.demo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class EmployeeController   {

    private final EmployeeService employeeService;
    private final OfficeService officeService;
    private final OfficeRepository officeRepository;
    private final ShipmentService shipmentService;


    @Autowired
    public EmployeeController(EmployeeService employeeService, OfficeService officeService, OfficeRepository officeRepository, ShipmentService shipmentService) {
        this.employeeService = employeeService;
        this.officeService=officeService;
        this.officeRepository = officeRepository;

        this.shipmentService = shipmentService;
    }



    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allEmployees(Model model) {
        model.addAttribute("model",employeeService.findAllEmployees());
        return "employee/all";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String register(Model model, @ModelAttribute(name="employeeDTO") EmployeeDTO employeeDTO) {
        model.addAttribute("offices", officeService.findAllOffices());
        model.addAttribute("employeeDTO", employeeDTO);
        return "employee/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String registerConfirm(Model model, @ModelAttribute(name="employeeDTO")EmployeeDTO employeeDTO) {

        Employee employee= this.employeeService.registerEmployee(employeeDTO);
        employee.getOffice().getEmployees().add(employee);
        officeRepository.saveAndFlush(employee.getOffice());
        model.addAttribute("employee", employee);
        model.addAttribute("model",employeeService.findAllEmployees());
        return "employee/all";

    }




    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteEmployee(@PathVariable String id, Model model) {
        Employee employee = this.employeeService.findEmployeeById(id);
        model.addAttribute("model", employee);
        return "employee/delete";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteEmployeeConfirm(@PathVariable String id) {
        this.employeeService.deleteEmployee(id);
        return "home";
    }

    @GetMapping("/employees/registeredShipments/{id}")
    @PreAuthorize("hasAnyRole('ROLE_DRIVER','ROLE_ADMIN')")
    public String registeredShipmentsByEmployee(@PathVariable String id,Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("shipments",shipmentService.findRegisteredShipmentsByEmployee(employee));
        return "shipment/all";
    }

//    @GetMapping("/employees/edit/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
//    public String edit(@PathVariable String id,Model model){
//        Employee employee = this.employeeService.findEmployeeById(id);
//        model.addAttribute("model", employee);
//        model.addAttribute("offices",officeRepository.findAll());
//        return "employee/edit";
//    }
//
//    @PostMapping("/edit")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String editConfirm(Model model, @ModelAttribute(name="employee")Employee employee) {
//
//        employeeService.updateEmployee(employee);
//        model.addAttribute("model",employeeService.findAllEmployees());
//        return "employee/all";
//
//    }




}
