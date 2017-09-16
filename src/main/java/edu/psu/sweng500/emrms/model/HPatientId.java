package edu.psu.sweng500.emrms.model;

public class HPatientId {
    private long hPatientId;
    private String userId;
    private String creationDateTime;
    private String idValue;
    private String idType;
    private String idIssuerName;
    private long idIssuerId;
    private long patientId;

    public long gethPatientId() {
        return hPatientId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public String getIdValue() {
        return idValue;
    }

    public String getIdType() {
        return idType;
    }

    public String getIdIssuerName() {
        return idIssuerName;
    }

    public long getIdIssuerId() {
        return idIssuerId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void sethPatientId(long hPatientId) {
        this.hPatientId = hPatientId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setIdIssuerName(String idIssuerName) {
        this.idIssuerName = idIssuerName;
    }

    public void setIdIssuerId(long idIssuerId) {
        this.idIssuerId = idIssuerId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
