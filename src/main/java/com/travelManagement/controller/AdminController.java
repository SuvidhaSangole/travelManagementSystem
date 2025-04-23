package com.travelManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;
import com.travelManagement.entity.User;
import com.travelManagement.service.DestinationService;
import com.travelManagement.service.TravelPackageService;
import com.travelManagement.service.UserService;

@RestController
@RequestMapping("travelManagement/admin")
public class AdminController {
	@Autowired
	TravelPackageService travelPackageService;

	@Autowired
	DestinationService destinationService;
	
	@Autowired
	UserService userService;

	@PostMapping("/createTravelPackage")
	public String createTravelPackage(@RequestBody TravelPackage travelPackage) {

		return travelPackageService.createTravelPackage(travelPackage);
	}

	@GetMapping("/getAllTravelPackage")
	public List<TravelPackage> getAllTravelPackages() {

		return travelPackageService.getAllTravelPackages();

	}

	@GetMapping("/getTravelPackageById/{packageId}")
	public TravelPackage getTravelPackageById(@PathVariable int packageId) {
		return travelPackageService.getTravelPackageById(packageId);

	}

	@PutMapping("/updateTravelPackageById/{packageId}")
	public String updateTravelPackageById(@PathVariable int packageId, @RequestBody TravelPackage newPackage) {
		return travelPackageService.updateTravelPackageById(packageId, newPackage);

	}

	@DeleteMapping("/deleteTravelPackageById/{packageId}")
	public String deleteTravelPackageById(@PathVariable int packageId) {
		return travelPackageService.deleteTravelPackageById(packageId);

	}

	@GetMapping("/getAllDestinations")
	public List<Destination> getAllDestinationss() {
		return destinationService.getAllDestinations();
	}

	@GetMapping("/getDestinationById/{destinationId}")
	public Destination getDestinationById(@PathVariable int destinationId) {
		return destinationService.getDestinationById(destinationId);

	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/getUserById/{userId}")
	public String getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);

	}
	@PutMapping("/updateUserById/{userId}")
	public String updateUserById(@PathVariable int userId,@RequestBody User newUser) {
		return userService.updateUserById(userId,newUser);

	}
	
	@DeleteMapping("/deleteUserById/{userId}")
	public String deleteUserById(@PathVariable int userId) {
		return userService.deleteUserById(userId);

	}
	
//	@PutMapping("/updateBooking/{bookingId}")
//	public String updateBooking(@PathVariable int bookingId) {
//		return userService.updateBooking(bookingId);
//
//	}



}
