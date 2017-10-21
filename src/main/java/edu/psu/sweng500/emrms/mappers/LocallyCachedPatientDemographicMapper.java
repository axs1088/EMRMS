package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.*;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedPatientDemographicMapper implements PatientDemographicsMapper {

    @Override
    public HPerson getPersonDetails(int personId) {
        return new HPerson();
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
}
