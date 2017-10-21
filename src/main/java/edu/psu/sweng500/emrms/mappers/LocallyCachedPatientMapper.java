package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedPatientMapper implements PatientMapper {
    List<HPatient> patientList;

    public LocallyCachedPatientMapper() {
        patientList = new ArrayList<>();
    }

    @Override
    public List<HPatient> readAll() {
        return patientList;
    }

    @Override
    public void deleteAll() {
        patientList.clear();
    }

    @Override
    public void create() {
        patientList.add(new HPatient());
    }

    @Override
    public void insertPatient(HPatient patient) {
        patientList.add(new HPatient());
    }

    @Override
    public void insertEncounterDetails(HEncounter hEncounter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertPerson(HPatient patient) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertPatientName(HPatient patient) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertPatientAddress(HPatient patient) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertPatientIdentifiers(HPatient patient) {
        // TODO Auto-generated method stub

    }


}
