package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.*;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedPatientDemographicMapper implements PatientDemographicsMapper {
    private HPerson person;

    public LocallyCachedPatientDemographicMapper() {
        person = new HPerson();
    }

    public void setPersonReturned(HPerson person) {
        this.person = person;
    }

    @Override
    public HPerson getPersonDetails(int personId) {
        return person;
    }

    @Override
    public Integer getNextPatientObjectID() {
        return null;
    }

    @Override
    public HName getPersonName(int personId) {
        return new HName();
    }

    @Override
    public Address getPersonAddress(int personId) {
        return new Address();
    }

    @Override
    public List<HPatientId> getPatientIdentifiers(int patientId) {
        return new ArrayList<>();
    }

    @Override
    public HPatient getPatientDetails(int patientId) {
        return new HPatient();
    }

    @Override
    public List<HEncounter> getPatientEncounters(int patientId) {
        return new ArrayList<>();
    }

    @Override
    public List<HAllergy> getPatientAllergies(int patientId) {
        return new ArrayList<>();
    }

    @Override
    public List<HDiagnosis> getPatientDiagnoses(int patientId) {
        return new ArrayList<>();
    }

    @Override
    public Phone getHomePhone(int personId) {
        return new Phone();
    }

    @Override
    public Phone getCellPhone(int personId) {
        return new Phone();
    }

    @Override
    public String getEmail(int personId) {
        return "";
    }
}
