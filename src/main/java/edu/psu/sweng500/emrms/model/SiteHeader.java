package edu.psu.sweng500.emrms.model;

public class SiteHeader {
    private String physicianName;
    private String clinicName;
    private String nameLastCommaFirst;
    private String birthDate;
    private String attending;
    private String mrNumber;
    private String ignoredSelectedAllergy;
    private String severeAllergyList;
    private String encounterType;
    private String encounterStatus;
    private String encounterStartDate;
    private String encounterNumber;
    private String ignoredSelectedDiagnosis;
    private String primaryDiagnosisList;

    public String getNameLastCommaFirst() {
        return nameLastCommaFirst;
    }

    public void setNameLastCommaFirst(String nameLastCommaFirst) {
        this.nameLastCommaFirst = nameLastCommaFirst;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAttending() {
        return attending;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }

    public String getMrNumber() {
        return mrNumber;
    }

    public void setMrNumber(String mrNumber) {
        this.mrNumber = mrNumber;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public String getEncounterStatus() {
        return encounterStatus;
    }

    public void setEncounterStatus(String encounterStatus) {
        this.encounterStatus = encounterStatus;
    }

    public String getEncounterStartDate() {
        return encounterStartDate;
    }

    public void setEncounterStartDate(String encounterStartDate) {
        this.encounterStartDate = encounterStartDate;
    }

    public String getEncounterNumber() {
        return encounterNumber;
    }

    public void setEncounterNumber(String encounterNumber) {
        this.encounterNumber = encounterNumber;
    }

    public String getIgnoredSelectedDiagnosis() {
        return ignoredSelectedDiagnosis;
    }

    public void setIgnoredSelectedDiagnosis(String getIgnoredSelectedDiagnosis) {
        this.ignoredSelectedDiagnosis = getIgnoredSelectedDiagnosis;
    }

    public String getIgnoredSelectedAllergy() {
        return ignoredSelectedAllergy;
    }

    public void setIgnoredSelectedAllergy(String ignoredSelectedAllergy) {
        this.ignoredSelectedAllergy = ignoredSelectedAllergy;
    }

    public String getPrimaryDiagnosisList() {
        return primaryDiagnosisList;
    }

    public void setPrimaryDiagnosisList(String primaryDiagnosisList) {
        this.primaryDiagnosisList = primaryDiagnosisList;
    }

    public String getSevereAllergyList() {
        return severeAllergyList;
    }

    public void setSevereAllergyList(String severeAllergyList) {
        this.severeAllergyList = severeAllergyList;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
