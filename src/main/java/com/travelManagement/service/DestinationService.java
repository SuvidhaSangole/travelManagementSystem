package com.travelManagement.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.dao.DestinationDao;
import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;

@Service
public class DestinationService {
	@Autowired
	DestinationDao destinationDao;

	public List<Destination> getAllDestinations() {

		return destinationDao.getAllDestinations();

	}

	public List<Destination> getAllDestinationsInfo() {

		return destinationDao.getAllDestinationsInfo();
	}

	public Destination getDestinationById(int destinationId) {
		Destination destination1 = destinationDao.getDestinationById(destinationId);
		if (destination1 != null) {
			return destination1;
		} else {
			return null;
		}
	}


}