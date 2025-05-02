/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Dao.AdminDao;
import model.VisaApply;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.User;
/**
 *
 * @author Dell
 */
public class AdminService {
    private final AdminDao adminDao;

    // Constructor to initialize AdminDao
    public AdminService() {
        adminDao = new AdminDao();
    }

    // Check Admin Credentials
    public boolean checkAdmin(String adminName, String adminPassword) {
        return "Harshita@123".equals(adminName) && "12345".equals(adminPassword);
    }

    // View all visa applications
    public List<VisaApply> viewAll() throws SQLException {
        return adminDao.getAll();
    }

    // Manage Users (View & Delete Users)
    public void manageUsers() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        List<User> users = adminDao.getAllUsers();
        
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println("Username: " + user.getUserName());
        }
        
        System.out.println("Enter Username to delete (or type 'exit' to cancel):");
        String userName = scanner.next();
        if (!userName.equalsIgnoreCase("exit")) {
            boolean success = adminDao.deleteUser(userName);
            System.out.println(success ? "User deleted successfully." : "Error deleting user.");
        }
    }

    // Manage Visa Applications (Approve, Reject, Delete)
   public boolean updateApplicationStatus(int appId, String status) throws SQLException {
        return adminDao.updateApplicationStatus(appId, status);
    }

    public boolean deleteApplication(int appId) throws SQLException {
        return adminDao.deleteApplication(appId);
    }

    // Search Visa Applications by User Name or Status
    public void searchApplications() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Search by: 1) User Name  2) Status");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        List<VisaApply> results = null;
        
        switch (choice) {
            case 1:
                System.out.println("Enter User Name:");
                String name = scanner.nextLine();
                results = adminDao.searchApplicationsByName(name);
                break;
            case 2:
                System.out.println("Enter Status (Approved/Rejected/Pending):");
                String status = scanner.nextLine();
                results = adminDao.searchApplicationsByStatus(status);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        if (results == null || results.isEmpty()) {
            System.out.println("No applications found.");
        } else {
            for (VisaApply application : results) {
                System.out.println("Application ID: " + application.getApplicationId() + ", User: " + application.getFullName() + ", Status: " + application.getStatus());
            }
        }
    }
}
