package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPatient;

import java.util.List;

public interface PatientService {
    List<HPatient> readAll();

    void deleteAll();

    HPatient createNew();
    
    public void registerPatient(HPatient patient) throws Exception;
    
    public void updatePatient(HPatient patient) throws Exception;

    
}