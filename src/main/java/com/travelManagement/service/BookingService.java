package com.travelManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.dao.BookingDao;
import com.travelManagement.dao.UserDao;
import com.travelManagement.entity.Booking;
import com.travelManagement.entity.TravelPackage;
import com.travelManagement.entity.User;

@Service
public class BookingService {

	@Autowired
	UserDao userDao;

	@Autowired
	BookingDao bookingDao;

	public Integer addBooking(Booking booking) {
		
		Integer id=bookingDao.addBooking(booking);
	return id;

	}

}
