package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HEncounter;

public interface SaveEncounterService {
    public int SaveEncounter(HPatient hPatient, HEncounter hEncounter);
}
