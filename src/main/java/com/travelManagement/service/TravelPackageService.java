package com.travelManagement.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.dao.DestinationDao;
import com.travelManagement.dao.TravelPackageDao;
import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;

@Service
public class TravelPackageService {
	@Autowired
	TravelPackageDao travelPackageDao;

	@Autowired
	DestinationDao destinationDao;

	public String createTravelPackage(TravelPackage travelPackage) {
		for (Destination destination : travelPackage.getDestinations()) {
			destination.setTravelPackage(travelPackage); // Ensure proper mapping
		}
		boolean isPackageCreated = travelPackageDao.createTravelPackage(travelPackage);
		if (isPackageCreated) {
			return "Travel Package Created Successfully: " + travelPackage.getPackageName();
		} else {
			return "Failed to create Travel Package ";
		}

	}

	public List<TravelPackage> getAllTravelPackages() {

		return travelPackageDao.getAllTravelPackages();
	}

	public TravelPackage getTravelPackageById(int packageId) {

		TravelPackage package1 = travelPackageDao.getTravelPackageById(packageId);
		return package1;
	}

	public List<TravelPackage> getAllTravelPackageInfo() {
		return travelPackageDao.getAllTravelPackageInfo();
	}

	public String updateTravelPackageById(int packageId, TravelPackage newPackage) {

		TravelPackage package1 = travelPackageDao.updateTravelPackageById(packageId, newPackage);

		for (Destination destination : newPackage.getDestinations()) {
			destination.setTravelPackage(newPackage); // Ensure proper mapping
		}
		if (package1 != null) {
			return "Travel Package with Id :" + package1.getPackageId() + " updated Successfully: "
					+ " Travel Package Name: " + package1.getPackageName() + " Desription:  "
					+ package1.getDescription();

		} else {
			return "Travel Package Is not updated";
		}
	}

	public String deleteTravelPackageById(int packageId) {
		boolean isDeleted = travelPackageDao.deleteTravelPackageById(packageId);
		if (isDeleted) {
			return "Travel Package With Id " + packageId + " Deleted Successfully...!!!";
		} else {
			return "Travel Package With Id  " + packageId + " Deletion Failed ...!!!";
		}
	}

}
