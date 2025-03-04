package com.travelManagement.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "travel_package")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id")
    private int packageId;

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private String description;

    private double price;
    private int durationDays;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Destination> destinations = new ArrayList<>();

    // Ensure bidirectional mapping
    public void addDestination(Destination destinationObj) {
        if (destinations == null) {
        	destinations = new ArrayList<>();
        }
        destinations.add(destinationObj);
        destinationObj.setTravelPackage(this); // Ensure each destination is linked
    }
    
    
    
    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    // Ensure bidirectional mapping
    public void addBooking(Booking bookingObj) {
        if (bookings == null) {
        	bookings = new ArrayList<>();
        }
        bookings.add(bookingObj);
        bookingObj.setTravelPackage(this);  // Ensure each destination is linked
    }
    
}
