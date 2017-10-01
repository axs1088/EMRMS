package edu.psu.sweng500.emrms.model;

public class PatientRegistrationModel {
    private String lastName;
    private String firstName;
    private String middleName;

    public PatientRegistrationModel() {
        firstName = "";
        middleName = "";
        lastName = "";
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
