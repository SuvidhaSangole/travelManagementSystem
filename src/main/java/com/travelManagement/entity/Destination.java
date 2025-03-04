package com.travelManagement.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "destination_id")
    private int destinationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
    

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    @JsonBackReference
    private TravelPackage travelPackage;
}
