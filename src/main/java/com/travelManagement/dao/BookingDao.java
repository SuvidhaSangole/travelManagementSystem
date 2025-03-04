package com.travelManagement.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public Integer addBooking(Booking booking) {
	int existedPackageId=booking.getTravelPackage().getPackageId();
	int existedUserId=booking.getUser().getUserId();
		
	Session session=factory.openSession();
	Criteria criteria=session.createCriteria(TravelPackage.class);
	criteria.add(Restrictions.eq("packageId", existedPackageId));
	criteria.uniqueResult();
	//return existedPackageId;
	
	Criteria userCriteria=session.createCriteria(User.class);
	userCriteria.add(Restrictions.eq("userId", existedUserId));
	System.out.println(existedUserId);
	userCriteria.uniqueResult();
	session.beginTransaction().commit();
	return existedUserId;
	
	

	}

	

}
