/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import java.sql.Connection;
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
public class UserDao {

    // Add a new user
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";

        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Validate user login
    public boolean validateUser(String userName, String password) {
        String sql = "SELECT user_name FROM users WHERE user_name = ? AND password = ?";
        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // If resultSet has a row, user exists
        } catch (SQLException e) {
            return false;
        }
    }

    // Create a new user (checking for existing username)
    public boolean createUser(String userName, String password) {
        String checkQuery = "SELECT user_name FROM users WHERE user_name = ?";
        String insertQuery = "INSERT INTO users (user_name, password) VALUES (?, ?)";

        try (Connection connection = Dao.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, userName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false; // User already exists
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, userName);
                insertStmt.setString(2, password);
                return insertStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            return false;
        }
    }

 
    public boolean addVisaApplication(VisaApply visaApply) {
        String sql = "INSERT INTO visa_applications (full_name, passport_number, date_of_birth, nationality, visa_type, visa_purpose, travel_start_date, travel_end_date, destination_country, status, application_date, application_fee, is_payment_completed, contact_number, email_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?, ?, ?)";
       
        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, visaApply.getFullName());
            statement.setString(2, visaApply.getPassportNumber());
            statement.setString(3, visaApply.getDateOfBirth());
            statement.setString(4, visaApply.getNationality());
            statement.setString(5, visaApply.getVisaType());
            statement.setString(6, visaApply.getVisaPurpose());
            statement.setString(7, visaApply.getTravelStartDate());
            statement.setString(8, visaApply.getTravelEndDate());
            statement.setString(9, visaApply.getDestinationCountry());
            statement.setString(10, "Pending"); 
            statement.setDouble(11, visaApply.getApplicationFee());
            statement.setBoolean(12, visaApply.isPaymentCompleted());
            statement.setString(13, visaApply.getContactNumber());
            statement.setString(14, visaApply.getEmailAddress());

           
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }



    // Update Visa Status
    public boolean updateVisaStatus(int applicationId, String newStatus) {
        String sql = "UPDATE visa_applications SET status = ? WHERE application_id = ?";

        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newStatus);
            statement.setInt(2, applicationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Get Visa Application by ID
    public VisaApply getVisaApplicationById(int applicationId) {
        String sql = "SELECT * FROM visa_applications WHERE application_id = ?";
        VisaApply visaApply = null;

        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, applicationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                visaApply = new VisaApply();
                visaApply.setApplicationId(resultSet.getInt("application_id"));
               
                visaApply.setFullName(resultSet.getString("full_name"));
                visaApply.setPassportNumber(resultSet.getString("passport_number"));
                visaApply.setNationality(resultSet.getString("nationality"));
                visaApply.setVisaType(resultSet.getString("visa_type"));
                visaApply.setVisaPurpose(resultSet.getString("visa_purpose"));
                visaApply.setApplicationFee(resultSet.getDouble("application_fee"));
                visaApply.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
        }
        return visaApply;
    }

    // Get Visa Applications by `user_name`
    public List<VisaApply> getApplicationsByUserName(String userName) {
        String sql = "SELECT * FROM visa_applications WHERE full_name = ?";
        List<VisaApply> visaList = new ArrayList<>();

        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VisaApply visaApply = new VisaApply();
                    visaApply.setApplicationId(resultSet.getInt("application_id"));
                    visaApply.setFullName(resultSet.getString("full_name"));
                    visaApply.setPassportNumber(resultSet.getString("passport_number"));
                    visaApply.setDateOfBirth(resultSet.getString("date_of_birth"));
                    visaApply.setNationality(resultSet.getString("nationality"));
                    visaApply.setVisaType(resultSet.getString("visa_type"));
                    visaApply.setVisaPurpose(resultSet.getString("visa_purpose"));
                    visaApply.setTravelStartDate(resultSet.getString("travel_start_date"));
                    visaApply.setTravelEndDate(resultSet.getString("travel_end_date"));
                    visaApply.setDestinationCountry(resultSet.getString("destination_country"));
                    visaApply.setStatus(resultSet.getString("status"));
                    visaApply.setApplicationDate(resultSet.getString("application_date"));
                    visaApply.setApplicationFee(resultSet.getDouble("application_fee"));
                    visaApply.setPaymentCompleted(resultSet.getBoolean("is_payment_completed"));
                    visaApply.setContactNumber(resultSet.getString("contact_number"));
                    visaApply.setEmailAddress(resultSet.getString("email_address"));

                    visaList.add(visaApply);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching visa applications for user: " + userName);
        }
        return visaList;
    }

    // Get All Visa Applications
    public List<VisaApply> getAllVisaApplications() {
        String sql = "SELECT * FROM visa_applications";
        List<VisaApply> visaList = new ArrayList<>();

        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                VisaApply visaApply = new VisaApply();
                visaApply.setApplicationId(resultSet.getInt("application_id"));
                
                visaApply.setFullName(resultSet.getString("full_name"));
                visaApply.setPassportNumber(resultSet.getString("passport_number"));
                visaApply.setNationality(resultSet.getString("nationality"));
                visaApply.setVisaType(resultSet.getString("visa_type"));
                visaApply.setVisaPurpose(resultSet.getString("visa_purpose"));
                visaApply.setApplicationFee(resultSet.getDouble("application_fee"));
                visaApply.setStatus(resultSet.getString("status"));
                visaList.add(visaApply);
            }
        } catch (SQLException e) {
        }
        return visaList;
    }

    public boolean updateVisaApplication(int appId, String newVisaType, String newPurpose) {
        String sql = "UPDATE visa_applications SET visa_type = ?, visa_purpose = ? WHERE application_id = ?";
        
        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newVisaType);
            statement.setString(2, newPurpose);
            statement.setInt(3, appId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }


}

