package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.*;

import java.util.List;

public interface GetPatientDemographicsService {

    public int getPersonId(int patientObjectId);

    public HPatient getPatientDemographics(int patientObjectId);

    public HPerson getPersonDetails(int patientObjectId);

    public HName getPersonName(int personId);

    public Address getPersonAddress(int personId);

    public List<HPatientId> getPatientIdentifiers(int patientObjectId);

    public List<HEncounter> getPatientEncounters(int patinetObjectId);

}
