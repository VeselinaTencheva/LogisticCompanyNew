package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/users")
public class UserController  {

	private final UserService userService;
	private final ShipmentService shipmentService;
//	private final CustomerController customerController;

	@Autowired
	public UserController(UserService userService, ShipmentService shipmentService) {
		this.userService = userService;
		this.shipmentService = shipmentService;
//		this.customerController = customerController;
	}

//	@GetMapping("/register")
//	@PreAuthorize("isAnonymous()")
//	public ModelAndView register() {
//		return super.view("register");
//	}
//
//	@PostMapping("/register")
//	@PreAuthorize("isAnonymous()")
//	public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) {
//		if (!model.getPassword().equals(model.getConfirmPassword())) {
//			return super.view("register");
//		}
//
//		this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));
////		this.userService.registerUser(this.modelMapper.map(model, User.class));
//
//
//		return super.redirect("/login");
//	}

	@GetMapping("/login")
	@PreAuthorize("isAnonymous()")
	public String login() {
		return "login";
	}


//	@GetMapping("/profile")
//	@PreAuthorize("isAuthenticated()")
//	public ModelAndView profile(Principal principal, User user) {
//		modelAndView.addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()),
//				UserProfileViewModel.class));
//				modelAndView.addObject("model", this.userService.findUserByUserName(principal.getName()));
//		return super.view("profile", modelAndView);
//	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String allUsers(Model model) {

		model.addAttribute("users", userService.findAllUsers());
		return "user/users-all";
	}

//	@GetMapping("/allEmployees")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ModelAndView allEmployees(ModelAndView modelAndView) {
//		modelAndView.addObject("users", userService.findAllEmployees());
//		return super.view("all-employees", modelAndView);
//	}
//
//	@GetMapping("/allClients")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
//	public ModelAndView allClients(ModelAndView modelAndView) {
//
//		modelAndView.addObject("users", userService.findAllCustomers());
//		return super.view("all-clients", modelAndView);
//	}


//	@PostMapping("/set-client/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ModelAndView setUser(@PathVariable String id) {
//		this.userService.setUserRole(id, "client");
//		return super.redirect("/users/all");
//	}
//
//	@PostMapping("/set-employee/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ModelAndView setEmployee(@PathVariable String id) {
//		this.userService.setUserRole(id, "employee");
//		return super.redirect("/users/all");
//	}
//
//	@PostMapping("/set-admin/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ModelAndView setAdmin(@PathVariable String id) {
//		this.userService.setUserRole(id, "admin");
//
//		return super.redirect("/users/all");
//	}

//	@GetMapping("/registerShipment/{employeeId}/{shipmentId}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
//	public ModelAndView registerShipment(@PathVariable String employeeId, @PathVariable String shipmentId, ModelAndView modelAndView ) {
//
////		UserServiceModel user = userService.findUserById(employeeId);
//		User user = userService.findUserById(employeeId);
//		Shipment shipment = shipmentService.findShipmentById(shipmentId);
//		userService.registerShipment(user,shipment);
//
//		modelAndView.addObject("user", user);
//		modelAndView.addObject("shipment",shipment);
//		return super.view("all-clients", modelAndView);
//	}


//	@PostMapping("/delete")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ModelAndView deleteUser(@RequestParam("userId") String userId, ModelAndView modelAndView) {
////
//		this.userService.deleteUser(userId);
//		return super.redirect("/users/allUsers");
//	}
//

}
