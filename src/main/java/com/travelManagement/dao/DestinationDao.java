package com.travelManagement.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;

@Repository
public class DestinationDao {
	@Autowired
	SessionFactory factory;

	public List<Destination> getAllDestinations(){
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Destination.class);

		List<Destination> desinationList= criteria.list();
		session.beginTransaction().commit();
		return desinationList;
	}

	public List<Destination> getAllDestinationsInfo() {
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Destination.class);
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("destinationId"))
				.add(Projections.property("name"))
				.add(Projections.property("description")));
		List<Destination> desinationList= criteria.list();
		session.beginTransaction().commit();
		return desinationList;
	}

	public Destination getDestinationById(int destinationId) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Destination.class);
		criteria.add(Restrictions.eq("destinationId", destinationId));
		Destination destination1 = (Destination) criteria.uniqueResult();
		session.beginTransaction().commit();
		return destination1;
		
	}


}
