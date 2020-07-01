package com.example.demo.controller;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Office;
import com.example.demo.service.CompanyService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/offices")
public class OfficeController  {
    private final OfficeService officeService;
    private final CompanyService companyService;
    private final EmployeeService employeeService;


    @Autowired
    public OfficeController(OfficeService officeService, CompanyService companyService, EmployeeService employeeService) {
        this.officeService = officeService;
        this.companyService=companyService;
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOffice(Model model, @ModelAttribute(name = "office") Office office) {
        model.addAttribute("office", office);
        model.addAttribute("offices",officeService.findAllOffices());
        return "office/add";
    }



    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOfficeConfirm( Model model,
                                         @ModelAttribute(name = "office") Office office) {

       this.officeService.createOffice(office);
        office.setCompany(companyService.findAll().get(0));
        model.addAttribute("office",office);
        model.addAttribute("offices",officeService.findAllOffices());
        return  "office/all";
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public String allOffices(Model model) {
        model.addAttribute("offices",officeService.findAllOffices());
        return  "office/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteOffice(@PathVariable String id, Model model) {
        Office office = this.officeService.findById(id);
        model.addAttribute("model", office);
        return "office/delete";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteOfficeConfirm(@PathVariable String id,Model model) {
        this.officeService.deleteOffice(id);
        model.addAttribute("offices",officeService.findAllOffices());
        return "office/all";
    }

    @GetMapping("/employees/all/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allEmployeesFromOffice(@PathVariable String id, Model model) {
        Office office = officeService.findById(id);
        model.addAttribute("model", employeeService.findEmployeesByOffice(office));
        return "employee/all";
    }

    @GetMapping("/offices/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(@PathVariable String id,Model model){
        Office office = this.officeService.findById(id);
        model.addAttribute("model", office);
        return "office/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editOfficeConfirm( Model model,
                                    @ModelAttribute(name = "office") Office office) {
        this.officeService.updateOffice(office);
        model.addAttribute("office",office);
        model.addAttribute("offices",officeService.findAllOffices());
        return  "office/all";
    }


    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public List<Office> fetchOffices() {
        List<Office> offices = this.officeService.findAllOffices();
        return offices;

    }
}
