package edu.psu.sweng500.emrms.model;

public class HDiagnosis {

    private int hDiagnosisId;
    private String userId;
    private  String code;
    private  String description;
    private int encounterID;
    private int patientID;

    public int gethDiagnosisId() {
        return hDiagnosisId;
    }

    public void sethDiagnosisId(int hDiagnosisId) {
        this.hDiagnosisId = hDiagnosisId;
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
}


