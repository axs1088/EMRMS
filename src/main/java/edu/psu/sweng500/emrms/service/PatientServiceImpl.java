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
    public void registerPatient(HPatient patient) {
    	patientMapper.insertPerson(patient);
    	patientMapper.insertPatient(patient);
    	patientMapper.insertPatientName(patient);
    	patientMapper.insertPatientAddress(patient);
    }

}
