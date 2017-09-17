package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.List;

public interface FindPatientService {
    public List<HCensus> getPatientListByDemogrpahics(String lName, String fName, Integer gender);
}
