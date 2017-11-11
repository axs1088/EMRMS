package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HBed;
import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HEncounter;

import java.util.List;

public interface PatientEncounterService {
    public int AddEncounter(HPatient hPatient, HEncounter hEncounter);
    public int ReviseEncounter(HPatient hPatient, HEncounter hEncounter);
    public List<HHealthcareOrganization> GetPatientLocations(String searchString);
    public List<HBed> GetPatientLocationBeds(String searchString, int locationId);
}
