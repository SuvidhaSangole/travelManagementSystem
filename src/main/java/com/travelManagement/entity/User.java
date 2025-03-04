package com.travelManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int userId;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	private String location;

	@Pattern(regexp = "^[7-9]\\d{9}$", message = "Invalid mobile number")
	@Column(nullable = false, unique = true)
	 @JsonProperty("mobile_number") // Custom JSON key name
	private String mobile;

	@Email(message = "Invalid Email Format")
	@NotBlank(message = "Email Is Required")
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must have at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character")
	@Column(nullable = false)
	private String password;

	
    private String userRole;//ADMIN,CUSTOMER
	

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Booking> bookings = new ArrayList<>();

    // Ensure bidirectional mapping
    public void addBooking(Booking bookingObj) {
    	  if (bookings == null) {
          	bookings = new ArrayList<>();
          }
        bookings.add(bookingObj);
        bookingObj.setUser(this); // Ensure each booking is linked
    }
    


}
