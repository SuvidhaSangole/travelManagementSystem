package com.travelManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.dao.BookingDao;
import com.travelManagement.dao.UserDao;
import com.travelManagement.entity.Booking;

@Service
public class BookingService {

	@Autowired
	UserDao userDao;

	@Autowired
	BookingDao bookingDao;

	public String addBooking(Booking booking) {
		booking.setBookingDateAndTime(LocalDateTime.now());
		booking.setBookingStatus("PENDING");
		boolean isBooked=bookingDao.addBooking(booking);
		if(isBooked) {
			return "Booking Done Sucessfully .... " 
					+"\n Booking Details: \n"+booking.getUser().getFullname()
					+"\nBooking Status: "+booking.getBookingStatus()+
					"\nBooking Time: "+booking.getBookingDateAndTime()+
					"\n Travel Package: "+booking.getTravelPackage().getDescription();
			
		}else {
			return "Booking Failed";
		}

	}

}
