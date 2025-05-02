/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class VisaApply {
    private int applicationId;
    private String fullName;
    private String passportNumber; 
    private String dateOfBirth;
    private String nationality; // Applicant's nationality
    private String visaType; // Tourist, Work, etc.
    private String visaPurpose; // Tourism, Business, Study, etc.
    private String travelStartDate; // Intended travel start date
    private String travelEndDate; // Intended travel end date
    private String destinationCountry; // Country where the applicant is traveling
    private String status; // Pending, Approved, Rejected
    private String applicationDate; // Date of application submission
    private double applicationFee; // Visa application fee
    private boolean isPaymentCompleted; // Whether the payment is completed
 
    private String contactNumber; // Applicant's contact number
    private String emailAddress; // Applicant's email address

   

    // Getters and Setters
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

  
    public String getFullName() {
    	 System.out.println("Debug - Constructor Full Name: " + this.fullName);
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaPurpose() {
        return visaPurpose;
    }

    public void setVisaPurpose(String visaPurpose) {
        this.visaPurpose = visaPurpose;
    }

   
    @Override
    public String toString() {
        return String.format(
            "[ID: %s | Name: %s | Passport: %s | DOB: %s | Nationality: %s | Visa Type: %s | Purpose: %s | " +
            "Travel: %s to %s | Destination: %s | Status: %s | Applied: %s | Fee: %s | Payment: %s | Contact: %s | Email: %s]",
            applicationId, fullName, passportNumber, dateOfBirth, nationality, 
            visaType, visaPurpose, travelStartDate, travelEndDate, 
            destinationCountry, status, applicationDate, applicationFee, 
            isPaymentCompleted ? "Completed" : "Pending", contactNumber, emailAddress
        );
    }


	public String getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(String travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public String getTravelEndDate() {
        return travelEndDate;
    }

    public void setTravelEndDate(String travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

   

    public double getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(double applicationFee) {
        this.applicationFee = applicationFee;
    }

    public VisaApply(String fullName2, String passportNumber2, String dateOfBirth2, String nationality2, String visaType2, String visaPurpose2, String travelStartDate2, String travelEndDate2, String destinationCountry2, String string, double applicationFee2, int isPaymentCompleted2, String contactNumber2, String emailAddress2) {
		super();
	}

	public VisaApply() {
		// TODO Auto-generated constructor stub
	}

	public VisaApply(String fullName, String passportNumber, String dateOfBirth, String nationality,
            String visaType, String visaPurpose, String travelStartDate, String travelEndDate,
            String destinationCountry, String status, double applicationFee, boolean isPaymentCompleted,
            String contactNumber, String emailAddress) {

this.fullName = fullName;
this.passportNumber = passportNumber;
this.dateOfBirth = dateOfBirth;
this.nationality = nationality;
this.visaType = visaType;
this.visaPurpose = visaPurpose;
this.travelStartDate = travelStartDate;
this.travelEndDate = travelEndDate;
this.destinationCountry = destinationCountry;
this.status = status;
this.applicationFee = applicationFee;
this.isPaymentCompleted = isPaymentCompleted;
this.contactNumber = contactNumber;
this.emailAddress = emailAddress;
}


	public boolean isPaymentCompleted() {
        return isPaymentCompleted;
    }

    public void setPaymentCompleted(boolean i) {
        this.isPaymentCompleted = i;
    }

  
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

 
}

