package edu.psu.sweng500.emrms.model;

public class HAllergy {

    private int allergyID ;
    private String userId;
    private String allergyName;
    private String allergyCode;
    private int allergyType;
    private String severity;
    private int patientID;

    public int getAllergyID() {
        return allergyID;
    }

    public void setAllergyID(int allergyID) {
        this.allergyID = allergyID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyCode() {
        return allergyCode;
    }

    public void setAllergyCode(String allergyCode) {
        this.allergyCode = allergyCode;
    }

    public int getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(int allergyType) {
        this.allergyType = allergyType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }




}
