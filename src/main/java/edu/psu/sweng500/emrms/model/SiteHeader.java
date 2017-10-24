package edu.psu.sweng500.emrms.model;

public class SiteHeader {
    private String nameLastCommaFirst;
    private String birthDate;
    private String attending;
    private String mrNumber;
    private String ignoredSelectedAllergy;
    private String encounterType;
    private String encounterStatus;
    private String encounterStartDate;
    private String encounterNumber;
    private String ignoredSelectedDiagnosis;

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
}
