package edu.psu.sweng500.emrms.model;

public class HAuditRecord {
    private int auditRecordID;
    private String userId ;
    private String creationDateTime ;
    private int policyId ;
    private int patient_ObjectID ;
    private int encounter_ObjectID;
    private String patientName;
    private String encounterID;
    private String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public int getAuditRecordID() {
        return auditRecordID;
    }

    public void setAuditRecordID(int auditRecordID) {
        this.auditRecordID = auditRecordID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public int getPatient_ObjectID() {
        return patient_ObjectID;
    }

    public void setPatient_ObjectID(int patient_ObjectID) {
        this.patient_ObjectID = patient_ObjectID;
    }

    public int getEncounter_ObjectID() {
        return encounter_ObjectID;
    }

    public void setEncounter_ObjectID(int encounter_ObjectID) {
        this.encounter_ObjectID = encounter_ObjectID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEncounterID() {
        return encounterID;
    }

    public void setEncounterID(String encounterID) {
        this.encounterID = encounterID;
    }


}
