package com.travelManagement.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travelManagement.entity.Booking;
import com.travelManagement.entity.TravelPackage;
import com.travelManagement.entity.User;

@Repository
public class BookingDao {

	@Autowired
	SessionFactory factory;

	public boolean addBooking(Booking booking) {
		int bookPackageId = booking.getTravelPackage().getPackageId();
		int bookUserId = booking.getUser().getUserId();

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(TravelPackage.class);
		
		criteria.add(Restrictions.eq("packageId", bookPackageId));
		TravelPackage existedPakage = (TravelPackage) criteria.uniqueResult();

		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("userId", bookUserId));
		User existedUser = (User) userCriteria.uniqueResult();
		
		booking.setTravelPackage(existedPakage);
		booking.setUser(existedUser);
		booking.setPaymentStatus("PENDING");
		session.save(booking);
		transaction.commit();
		
		if (existedUser != null) {
			if (existedPakage != null) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

}
