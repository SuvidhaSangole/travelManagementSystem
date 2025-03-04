package com.travelManagement.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.dao.UserDao;
import com.travelManagement.entity.Booking;
import com.travelManagement.entity.Destination;
import com.travelManagement.entity.TravelPackage;
import com.travelManagement.entity.User;
import com.travelManagement.theme.BharatTrailsInfo;

import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public String registerUser(User user) {
		user.setUserRole("CUSTOMER");
		boolean isRegister = false;
		String password = user.getPassword();
		String newPass = null;
		if (password != null) {
			isRegister = userDao.registerUser(user);
		} else {
			String pass1 = user.getEmail().substring(0, 3);
			String pass2 = "$" + user.getMobile().substring(0, 3);
			newPass = pass1.concat(pass2);
			password = newPass;
			user.setPassword(password);
			isRegister = userDao.registerUser(user);
		}

		if (isRegister) {
			return user.getUserRole() + " : You have Registered Successfully...!!!"
					+ "You Can  Login To Explore Travel Packages " + "With Your Usename/Email: ";

		} else {
			return "Registration Failed...!!!";
		}

	}

	public String loginUserByUsernameOrEmail(User user) {
		boolean isLogin = false;
		String username = user.getUsername();
		String email = user.getEmail();
		String password = user.getPassword();
		if ((user.getUsername() == null || user.getUsername().isEmpty())
				&& (user.getEmail() == null || user.getEmail().isEmpty())) {
			isLogin = false;
			return "UserNamr Or Email And Password is Required";
		} else {
			User existingUser = userDao.loginUserByUsernameOrEmail(email, username, password);
			if (existingUser != null) {
				isLogin = true;

			} else {
				isLogin = false;
				return "You are Not Registered Yet..." + "Do Register To Explore ";
			}
		}

		if (isLogin) {
			return "Login SuccessFull\n" + " *****....WELCOME....***** \n" + ""
					+ BharatTrailsInfo.displayBharatTrailsInfo();

		} else {
			return "Login Failed...!!!";
		}
	}

	public List<User> getAllUsers() {

		return userDao.getAllUsers();
	}

	public String getUserById(int userId) {
		User user1 = userDao.getUserById(userId);
		if (user1 != null) {
			return "User Detail Is:\n" + user1.getFullname() + "\n" + user1.getEmail() + "\n" + user1.getMobile();
		} else {
			return userId + " : User Not Found ";
		}

	}
	public String updateUserById(int userId,User newUser) {
		boolean isUpdated=userDao.updateUserById(userId,newUser);
		if(isUpdated) {
			return "User Updated Successfully...!!!"+newUser.getFullname();
		}else {
			return "User iS Not Present..";
		}
	}


	public String deleteUserById(int userId) {
		boolean isDeleted=userDao.deleteUserById(userId);
		if(isDeleted) {
			return "User With userId:  "+userId+" : Deleted Successfully";
		}
		return "User Deletion Failed";
	}

	

	
}
