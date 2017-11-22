package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PatientEncounterMapper;
import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            encounterMapper.insertOPEncounterDetails(encounter);
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
    public int AddInPatientEncounter(HPatient hPatient, HEncounter encounter) {
        int encounterObjectID = encounter.getHEncounterID();
        if (encounterObjectID == 0){
            encounterMapper.insertIPEncounterDetails(encounter);
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
        encounterMapper.reviseOPEncounterDetails(encounter);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Encounter");
        auditRecord.setPolicyId(8);
        auditRecord.setPatient_ObjectID(encounter.getPatient_ObjectID());
        auditRecord.setEncounter_ObjectID(encounter.getHEncounterID());
        auditRecord.setPatientName(patientUtils.getPatientName(encounter.getPatient_ObjectID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public int ReviseInPatientEncounter(HPatient hPatient, HEncounter encounter) {
        encounterMapper.reviseIPEncounterDetails(encounter);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Encounter");
        auditRecord.setPolicyId(8);
        auditRecord.setPatient_ObjectID(encounter.getPatient_ObjectID());
        auditRecord.setEncounter_ObjectID(encounter.getHEncounterID());
        auditRecord.setPatientName(patientUtils.getPatientName(encounter.getPatient_ObjectID()));
        auditEventService.auditEvent(auditRecord);
        return 0;
    }

    @Override
    public List<HHealthcareOrganization> GetPatientLocations() {
        return encounterMapper.getPatientLocations();
    }
    
    @Override
    public HHealthcareOrganization getPatientLocationByObjectId(int encounterLocationObjectID) {
    	return encounterMapper.getPatientLocationByObjectId(encounterLocationObjectID);
    }

    @Override
    public List<HBed> GetPatientLocationBeds(String searchString, int locationId) {
        return encounterMapper.getPatientLocationBeds(searchString, locationId);
    }
}
