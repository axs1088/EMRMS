package edu.psu.sweng500.emrms.model;

public class HName {
    private long ObjectID;
    private String userId;
    private String creationDateTime;
    private String lastName;
    private String firstName;
    private Boolean active;

    public long getObjectID() {
        return ObjectID;
    }

    public void setObjectID(long objectID) {
        ObjectID = objectID;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
