package edu.psu.sweng500.emrms.models;

//Added by Atul Singh 09/01/2017
public class HPatient {
    private long ObjectID;
    private String userId;
    private String creationDateTime;
    private String mPINumber;
    private Boolean organDonor;
    private Boolean deceasedIndicator;
    private Boolean isPatientUndentified;
    private String primarylang;
    private Boolean medHistoryConsent;

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

    public String getmPINumber() {
        return mPINumber;
    }

    public void setmPINumber(String mPINumber) {
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
        return isPatientUndentified;
    }

    public void setPatientUndentified(Boolean patientUndentified) {
        isPatientUndentified = patientUndentified;
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
}
