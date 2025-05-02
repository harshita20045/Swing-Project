/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Dao.UserDao;

import java.util.Collections;
import model.VisaApply;
import java.sql.SQLException;
import java.util.List;
import model.User;
/**
 *
 * @author Dell
 */public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public boolean authenticateUser(String userName, String password) throws SQLException {
        if (userName == null || password == null || userName.trim().isEmpty() || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty.");
        }
        return userDao.validateUser(userName, password);
    }

    public boolean registerUser(User newUser) throws SQLException {
        if (newUser == null || newUser.getUserName() == null || newUser.getPassword() == null) {
            System.out.println("Error: User details cannot be null.");
            return false;
        }

        return userDao.createUser(newUser.getUserName(), newUser.getPassword());
    }

    public boolean applyForVisa(VisaApply visaApply) {
    	
        return userDao.addVisaApplication(visaApply);
    }

    public boolean updateVisaStatus(int applicationId, String newStatus) {
        if (applicationId <= 0) {
            throw new IllegalArgumentException("Error: Invalid Application ID.");
        }

        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Status cannot be empty.");
        }

        return userDao.updateVisaStatus(applicationId, newStatus);
    }

    public VisaApply getVisaApplicationDetails(int applicationId) {
        if (applicationId <= 0) {
            throw new IllegalArgumentException("Error: Invalid Application ID.");
        }
        return userDao.getVisaApplicationById(applicationId);
    }

    public List<VisaApply> viewApplicationStatus(String name) {
        
        return userDao.getApplicationsByUserName(name);
    }

    public boolean updateApplication(int appId, String newVisaType, String newPurpose) {
       
        return userDao.updateVisaApplication(appId, newVisaType, newPurpose);
    }


    public List<VisaApply> getAllVisaApplications() {
        List<VisaApply> applications = userDao.getAllVisaApplications();
        return (applications != null) ? applications : Collections.emptyList();
    }
}

