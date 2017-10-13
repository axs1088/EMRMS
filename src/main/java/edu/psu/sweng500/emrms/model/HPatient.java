package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.RatifiedDate;
import edu.psu.sweng500.emrms.validators.annotations.EmailFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Size;

//Added by Atul Singh 09/01/2017
public class HPatient {
    private int objectID;
    private String userId;
    private String creationDateTime;
    private String mPINumber;
    private Boolean organDonor;
    private Boolean deceasedIndicator;
    private Boolean isPatientUnidentified;
    private String primarylang;
    private Boolean medHistoryConsent;

    @NotEmpty(message = "Must specify birth date")
    private RatifiedDate birthDate;
    private long gender;
    private int personId;

    @Valid
    private ComplexName name;

    @Valid
    private Address address;

    @Valid
    private Phone cellPhone;

    @Valid
    private Phone homePhone;


    /**
     * The email.
     */
    @Size(max = 60)
    @EmailFormat
    private String email;

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
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

    public String getMPINumber() {
        return mPINumber;
    }

    public void setMPINumber(String mPINumber) {
        this.mPINumber = mPINumber;
    }

    public Boolean getOrganDonor() {
        return organDonor;
    }

    public void setOrganDonor(Boolean organDonor) {
        this.organDonor = organDonor;
    }

    public Boolean getDeceasedIndicator() {
        return deceasedIndicator;
    }

    public void setDeceasedIndicator(Boolean deceasedIndicator) {
        this.deceasedIndicator = deceasedIndicator;
    }

    public Boolean getPatientUndentified() {
        return isPatientUnidentified;
    }

    public void setPatientUndentified(Boolean patientUndentified) {
        isPatientUnidentified = patientUndentified;
    }

    public String getPrimarylang() {
        return primarylang;
    }

    public void setPrimarylang(String primarylang) {
        this.primarylang = primarylang;
    }

    public Boolean getMedHistoryConsent() {
        return medHistoryConsent;
    }

    public void setMedHistoryConsent(Boolean medHistoryConsent) {
        this.medHistoryConsent = medHistoryConsent;
    }

    public Boolean getIsPatientUnidentified() {
        return isPatientUnidentified;
    }

    public void setIsPatientUnidentified(Boolean isPatientUnidentified) {
        this.isPatientUnidentified = isPatientUnidentified;
    }

    public RatifiedDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(RatifiedDate birthDate) {
        this.birthDate = birthDate;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public ComplexName getName() {
        return name;
    }

    public void setName(ComplexName name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Phone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Phone getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Phone homePhone) {
        this.homePhone = homePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }


}
