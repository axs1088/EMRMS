package edu.psu.sweng500.emrms.model;


import java.sql.Date;

public class PatientRegistrationModel {
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer gender;
    private Date birthDate;
    private String streetAddress;
    private Boolean mailingMatchesStreet;
    private String city;
    private String state;
    private String zip;
    private String cellPhone;
    private String homePhone;
    private String email;
    private Boolean organDonor;

    public PatientRegistrationModel() {
        firstName = "";
        middleName = "";
        lastName = "";
        setGender(1); // Default to male
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Boolean getMailingMatchesStreet() {
        return mailingMatchesStreet;
    }

    public void setMailingMatchesStreet(Boolean mailingMatchesStreet) {
        this.mailingMatchesStreet = mailingMatchesStreet;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOrganDonor() {
        return organDonor;
    }

    public void setOrganDonor(Boolean organDonor) {
        this.organDonor = organDonor;
    }
}
