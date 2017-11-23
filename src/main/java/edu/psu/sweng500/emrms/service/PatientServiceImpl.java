package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.mappers.PatientMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.util.PersonPatientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("patientService")
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientMapper patientMapper;

    @Autowired
    private AuditEventService auditEventService;

    @Autowired
    private PersonPatientUtils patientUtils;

    @Autowired
    private ApplicationSessionHelper sessionHelper;

    public void setPatientMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @Override
    public List<HPatient> readAll() {
        return patientMapper.readAll();
    }

    @Override
    public void deleteAll() {
        patientMapper.deleteAll();
    }

    @Override
    public HPatient createNew() {
        return null;
    }

    @Override
    @Transactional
    public void registerPatient(HPatient patient) throws Exception {
        patientMapper.insertPerson(patient);
        patientMapper.insertPatient(patient);
        patientMapper.insertPatientName(patient);
        patientMapper.insertPatientAddress(patient);
        patientMapper.insertPatientIdentifiers(patient);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Register Patient");
        auditRecord.setUserId(sessionHelper.getPhysicianName());
        auditRecord.setPolicyId(5);
        auditRecord.setPatient_ObjectID(patient.getObjectID());
        auditRecord.setPatientName(patientUtils.getPatientName(patient.getObjectID()));
        auditEventService.auditEvent(auditRecord);
    }
    
    @Override
    @Transactional
    public void updatePatient(HPatient patient) throws Exception {
	    patientMapper.revisePerson(patient);
	    patientMapper.revisePersonName(patient);
	    patientMapper.revisePersonAddress(patient);
        HAuditRecord auditRecord = new HAuditRecord();
        auditRecord.setEventName("Revise Patient");
        auditRecord.setUserId(sessionHelper.getPhysicianName());
        auditRecord.setPolicyId(6);
        auditRecord.setPatient_ObjectID(patient.getObjectID());
        auditRecord.setPatientName(patientUtils.getPatientName(patient.getObjectID()));
        auditEventService.auditEvent(auditRecord);
    }

}
