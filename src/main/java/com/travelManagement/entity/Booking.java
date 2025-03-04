package com.travelManagement.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
	public class Booking {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "package_id", nullable = false)
	    private TravelPackage travelPackage;

	    @Column(name = "booking_date")
	    private Date bookingDate;

	    @Column(name = "status")
	    private String bookingStatus; // e.g., CONFIRMED, PENDING, CANCELED

	    @Column(name = "payment_status")
	    private String paymentStatus; // PAID, UNPAID

	}




