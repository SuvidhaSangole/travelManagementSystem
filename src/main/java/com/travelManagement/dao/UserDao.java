package com.travelManagement.dao;

import java.util.List;

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
public class UserDao {
	@Autowired
	SessionFactory factory;

	public boolean registerUser(User user) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		session.save(user);
		transaction.commit();
		session.close();

		return true;

	}

	public User loginUserByUsernameOrEmail(String email, String username, String password) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		if (username != null && !username.isEmpty()) {
			criteria.add(Restrictions.eq("username", username));
		} else if (email != null && !email.isEmpty()) {
			criteria.add(Restrictions.eq("email", email));
		}

		criteria.add(Restrictions.eq("password", password));

		User existingUser = (User) criteria.uniqueResult();
		return existingUser;

	}

	public List<User> getAllUsers() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> userList = criteria.list();
		session.beginTransaction().commit();
		return userList;
	}

	public User getUserById(int userId) {
		Session session = factory.openSession();
		User userById = session.get(User.class, userId);

		return userById;
	}

	public boolean updateUserById(int userId, User newUser) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		// Fetch existing user
		User existingUser = session.get(User.class,userId);
		if (existingUser == null) {
			return false; // User not found
		}

		// Update only non-null fields
		if (newUser.getFullname() != null) {
			existingUser.setFullname(newUser.getFullname());
		}
		if (newUser.getLocation() != null) {
			existingUser.setLocation(newUser.getLocation());
		}
		if (newUser.getMobile() != null) {
			existingUser.setMobile(newUser.getMobile());
		}
		if (newUser.getEmail() != null) {
			existingUser.setEmail(newUser.getEmail());
		}
		if (newUser.getPassword() != null) {
			existingUser.setPassword(newUser.getPassword());
		}

		session.update(existingUser);
		transaction.commit();
		return true;
	}
	

	public boolean deleteUserById(int userId) {
		Session session = factory.openSession();
		User user1 = session.get(User.class, userId);
		if (user1 != null) {
			session.delete(user1); // Delete the entity
			session.beginTransaction().commit();
			return true;

		} else {
			return false; // Travel package not found
		}

	}

	
}
