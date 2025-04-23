package com.travelManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.entity.Booking;
import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;
import com.travelManagement.entity.User;
import com.travelManagement.service.BookingService;
import com.travelManagement.service.DestinationService;
import com.travelManagement.service.TravelPackageService;
import com.travelManagement.service.UserService;

@RestController
@RequestMapping("travelManagement/customer")
public class UserController {

	@Autowired
	TravelPackageService travelPackageService;

	@Autowired
	DestinationService destinationService;

	@Autowired
	UserService userService;
	
	@Autowired
	BookingService bookingService; 
	
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@PostMapping("/loginUser")
	public String loginUserByUsernameOrEmail(@RequestBody User user) {
		return userService.loginUserByUsernameOrEmail(user);

	}

	@GetMapping("/getAllTravelPackageInfo")
	public List<TravelPackage> getAllTravelPackageInfo() {
		return travelPackageService.getAllTravelPackageInfo();

	}

	@GetMapping("/getAllDestinationsInfo")
	public List<Destination> getAllDestinationsInfo() {
		return destinationService.getAllDestinationsInfo();
	}
	
	@PostMapping("/addBooking")
	public String addBooking(@RequestBody Booking booking) {
		return 	bookingService.addBooking(booking);
	}


}