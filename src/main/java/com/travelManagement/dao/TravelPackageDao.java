package com.travelManagement.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;

@Repository
public class TravelPackageDao {
	@Autowired
	SessionFactory factory;

	public boolean createTravelPackage(TravelPackage travelPackage) {
		Session session = factory.openSession();
		session.saveOrUpdate(travelPackage);
		session.beginTransaction().commit();// Saves both TravelPackage and Destinations

		return true;

	}

	public List<TravelPackage> getAllTravelPackages() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TravelPackage.class);
		List<TravelPackage> packageList = criteria.list();
		session.beginTransaction().commit();
		return packageList;

	}

	public TravelPackage getTravelPackageById(int packageId) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TravelPackage.class);
		criteria.add(Restrictions.eq("packageId", packageId));
		TravelPackage package1 = (TravelPackage) criteria.uniqueResult();
		session.beginTransaction().commit();
		return package1;

	}

	public List<TravelPackage> getAllTravelPackageInfo() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TravelPackage.class);

		criteria.setProjection(Projections.projectionList().add(Projections.property("packageId"))
				.add(Projections.property("packageName")).add(Projections.property("description"))
				.add(Projections.property("price")).add(Projections.property("durationDays")));
		List<TravelPackage> packageList = criteria.list();

		session.beginTransaction().commit();
		return packageList;

	}

	public TravelPackage updateTravelPackageById(int packageId, TravelPackage newPackage) {
		Session session = factory.openSession();
		TravelPackage existingPackage = session.get(TravelPackage.class, packageId);
		if (existingPackage != null) {
			existingPackage.setPackageName(newPackage.getPackageName());
			existingPackage.setDescription(newPackage.getDescription());
			existingPackage.setPrice(newPackage.getPrice());
			existingPackage.setDurationDays(newPackage.getDurationDays());

			if (newPackage.getDestinations() != null) {
				existingPackage.getDestinations().clear(); // Remove old destinations
				for (Destination dest : newPackage.getDestinations()) {
					dest.setTravelPackage(existingPackage); // Set parent reference
					existingPackage.getDestinations().add(dest);
				}
			}
			session.update(existingPackage);
		} else {
			throw new EntityNotFoundException("Travel Package Not Found" + packageId);
		}
		session.beginTransaction().commit();
		return existingPackage;

	}

	public boolean deleteTravelPackageById(int packageId) {
		Session session = factory.openSession();
		TravelPackage package1 = session.get(TravelPackage.class, packageId);
		if (package1 != null) {
			session.delete(package1); // Delete the entity
			session.beginTransaction().commit();
			return true;

		} else {
			return false; // Travel package not found
		}

	}

}
