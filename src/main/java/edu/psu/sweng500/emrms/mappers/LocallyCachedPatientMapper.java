package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HPatient;

import java.util.List;

public class LocallyCachedPatientMapper implements PatientMapper {
    List<HPatient> patientList;

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
}
