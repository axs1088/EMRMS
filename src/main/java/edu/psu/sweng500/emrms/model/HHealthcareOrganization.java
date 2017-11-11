package edu.psu.sweng500.emrms.model;

public class HHealthcareOrganization {

    private int objectId;
    private String creationDateTime;
    private String name;
    private String abbreviation;
    private boolean active;
    private int idIssuerId;
    private boolean isIdIssuer;

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getIdIssuerId() {
        return idIssuerId;
    }

    public void setIdIssuerId(int idIssuerId) {
        this.idIssuerId = idIssuerId;
    }

    public boolean isIdIssuer() {
        return isIdIssuer;
    }

    public void setIdIssuer(boolean idIssuer) {
        isIdIssuer = idIssuer;
    }
}
