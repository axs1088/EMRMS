package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.List;

public interface CensusService {
    public List<HCensus> getPhysicianCensus(Integer userObjectID);

    public List<HCensus> getNurseCensus(Integer locationObjectID);

    public List<HCensus> getPatientListByDemographics(String lName, String fName, Integer gender);
}
