package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HEncounter;

public interface SaveEncounterService {
    public int AddEncounter(HPatient hPatient, HEncounter hEncounter);
    public int ReviseEncounter(HPatient hPatient, HEncounter hEncounter);
}
