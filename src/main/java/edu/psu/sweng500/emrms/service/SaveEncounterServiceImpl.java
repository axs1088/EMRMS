package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.SaveEncounterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HEncounter;
import org.springframework.stereotype.Service;

@Service("saveEncounterService")

public class SaveEncounterServiceImpl implements  SaveEncounterService{
    public void setEncounterMapper(SaveEncounterMapper encounterMapper) {
        this.encounterMapper = encounterMapper;
    }

    @Autowired
    private SaveEncounterMapper encounterMapper;


    @Override
    public int SaveEncounter(HPatient hPatient, HEncounter hEncounter) {
        encounterMapper.insertEncounterDetails(hEncounter);
        return 0;
    }
}
