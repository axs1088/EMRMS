package edu.psu.sweng500.emrms.model;

public class HDiagnosis {

    private int diagnosisObjectId;
    private String userId;
    private  String code;
    private  String description;
    private int encObjectId;
    private int patientID;
    private int priority;
    private String encounterId;
    private String encStartDateTime;
    private String encType;

    public int getDiagnosisObjectId() {
        return diagnosisObjectId;
    }

    public void setDiagnosisObjectId(int diagnosisObjectId) {
        this.diagnosisObjectId = diagnosisObjectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEncObjectId() {
        return encObjectId;
    }

    public void setEncObjectId(int encObjectId) {
        this.encObjectId = encObjectId;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getEncStartDateTime() {
        return encStartDateTime;
    }

    public void setEncStartDateTime(String encStartDateTime) {
        this.encStartDateTime = encStartDateTime;
    }

    public String getEncType() {
        return encType;
    }

    public void setEncType(String encType) {
        this.encType = encType;
    }
}


