package edu.psu.sweng500.emrms.model;

public class HDiagnosis {

    private int diagnosisObjectId;
    private String userId;
    private  String code;
    private  String description;
    private int encounterID;
    private int patientID;
    private int priority;

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

    public int getEncounterID() {
        return encounterID;
    }

    public void setEncounterID(int encounterID) {
        this.encounterID = encounterID;
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
}


