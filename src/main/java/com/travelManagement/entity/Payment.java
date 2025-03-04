//package com.travelManagement.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//import com.travelManagement.entity.enumPkg.PaymentStatus;
//
//import lombok.Getter;
//import lombok.Setter;
//@Getter
//@Setter
//@Entity
//public class Payment {
//	
//	    @Id
//		@GeneratedValue(strategy = GenerationType.AUTO)
//	    private int payment_id;
//
//	    private double amount;
//
//	    @Enumerated(EnumType.STRING)
//	    private PaymentStatus paymentStatus; // SUCCESS, FAILED, PENDING
//
//	    private LocalDateTime paymentDate;
//
//	    private String transactionid;
//
//	}
//
//
//
