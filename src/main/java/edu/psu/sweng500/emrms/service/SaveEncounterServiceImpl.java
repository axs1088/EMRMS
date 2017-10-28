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
    public int SaveEncounter(HPatient hPatient, HEncounter encounter) {
        int encounterObjectID = encounter.getHEncounterID();
        if (encounterObjectID == 0){
            encounterMapper.insertEncounterDetails(encounter);
        }
        else
        {
            encounterMapper.reviseEncounterDetails(encounter);
        }
        return 0;
    }
}
