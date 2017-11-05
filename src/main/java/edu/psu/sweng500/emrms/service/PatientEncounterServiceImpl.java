package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PatientEncounterMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HEncounter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("saveEncounterService")

public class PatientEncounterServiceImpl implements  PatientEncounterService{
    public void setEncounterMapper(PatientEncounterMapper encounterMapper) {
        this.encounterMapper = encounterMapper;
    }

    @Autowired
    private PatientEncounterMapper encounterMapper;

    @Autowired
    private AuditEventService auditEventService;

    @Autowired
    private PersonPatientUtils patientUtils;

    @Override
    @Transactional
    public int AddEncounter(HPatient hPatient, HEncounter encounter) {
        int encounterObjectID = encounter.getHEncounterID();
        if (encounterObjectID == 0){
            encounterMapper.insertEncounterDetails(encounter);
            HAuditRecord auditRecord = new HAuditRecord();
            auditRecord.setEventName("Add Encounter");
            auditRecord.setPolicyId(7);
            auditRecord.setPatient_ObjectID(encounter.getPatient_ObjectID());
            auditRecord.setEncounter_ObjectID(encounter.getHEncounterID());
            auditRecord.setPatientName(patientUtils.getPatientName(encounter.getPatient_ObjectID()));
            auditEventService.auditEvent(auditRecord);
        }
        return 0;
    }

    @Override
    @Transactional
    public int ReviseEncounter(HPatient hPatient, HEncounter encounter) {
        encounterMapper.reviseEncounterDetails(encounter);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Encounter");
        auditRecord.setPolicyId(8);
        auditRecord.setPatient_ObjectID(encounter.getPatient_ObjectID());
        auditRecord.setEncounter_ObjectID(encounter.getHEncounterID());
        auditRecord.setPatientName(patientUtils.getPatientName(encounter.getPatient_ObjectID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }
}
