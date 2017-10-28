package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PatientMapper;
import edu.psu.sweng500.emrms.model.HPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("patientService")
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientMapper patientMapper;

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
        int patientObjectID = patient.getObjectID();
        if(patientObjectID == 0){
            patientMapper.insertPerson(patient);
            patientMapper.insertPatient(patient);
            patientMapper.insertPatientName(patient);
            patientMapper.insertPatientAddress(patient);
            patientMapper.insertPatientIdentifiers(patient);
        }
        else {
            patientMapper.revisePerson(patient);
            patientMapper.revisePersonName(patient);
            patientMapper.revisePersonAddress(patient);
        }

    }

}
