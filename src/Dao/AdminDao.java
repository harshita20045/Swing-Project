/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.VisaApply;
/**
 *
 * @author Dell
 */
public class AdminDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Fetch all visa applications
    public List<VisaApply> getAll() throws SQLException {
        List<VisaApply> visaList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM visa_applications");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                VisaApply visa = new VisaApply();
                visa.setApplicationId(rs.getInt("application_id"));
                visa.setFullName(rs.getString("full_name"));
                visa.setPassportNumber(rs.getString("passport_number"));
                visa.setDateOfBirth(rs.getString("date_of_birth"));
                visa.setNationality(rs.getString("nationality"));
                visa.setVisaType(rs.getString("visa_type"));
                visa.setVisaPurpose(rs.getString("visa_purpose"));
                visa.setTravelStartDate(rs.getString("travel_start_date"));
                visa.setTravelEndDate(rs.getString("travel_end_date"));
                visa.setDestinationCountry(rs.getString("destination_country"));
                visa.setStatus(rs.getString("status"));
                visa.setApplicationDate(rs.getString("application_date"));
                visa.setApplicationFee(rs.getInt("application_fee"));
                visa.setPaymentCompleted(rs.getBoolean("is_payment_completed"));
                visa.setContactNumber(rs.getString("contact_number"));
                visa.setEmailAddress(rs.getString("email_address"));
                visaList.add(visa);
            }
        }
        return visaList;
    }

    // Update visa application status
    public boolean updateApplicationStatus(int appId, String status) throws SQLException {
        String query = "UPDATE visa_applications SET status = ? WHERE application_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, appId);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Delete visa application
    public boolean deleteApplication(int appId) throws SQLException {
        String query = "DELETE FROM visa_applications WHERE application_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appId);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Search visa applications by name
    public List<VisaApply> searchApplicationsByName(String name) throws SQLException {
        List<VisaApply> visaList = new ArrayList<>();
        String query = "SELECT * FROM visa_applications WHERE full_name LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    VisaApply visa = new VisaApply();
                    visa.setApplicationId(rs.getInt("application_id"));
                    visa.setFullName(rs.getString("full_name"));
                    visa.setStatus(rs.getString("status"));
                    visaList.add(visa);
                }
            }
        }
        return visaList;
    }

    // Search visa applications by status
    public List<VisaApply> searchApplicationsByStatus(String status) throws SQLException {
        List<VisaApply> visaList = new ArrayList<>();
        String query = "SELECT * FROM visa_applications WHERE status = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    VisaApply visa = new VisaApply();
                    visa.setApplicationId(rs.getInt("application_id"));
                    visa.setFullName(rs.getString("full_name"));
                    visa.setStatus(rs.getString("status"));
                    visaList.add(visa);
                }
            }
        }
        return visaList;
    }

    // Delete a user
    public boolean deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE user_name = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Fetch all users
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        }
        return userList;
    }
}

