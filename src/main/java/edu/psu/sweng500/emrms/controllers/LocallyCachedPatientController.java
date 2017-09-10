package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.exceptions.PatientNotFoundException;
import edu.psu.sweng500.emrms.model.HPatient;

import java.util.ArrayList;
import java.util.List;

/**
 * Used primarily by unit tests to bypass database layer
 */
public class LocallyCachedPatientController implements PatientController {

    List<HPatient> hPatientList;

    public LocallyCachedPatientController() {
        hPatientList = new ArrayList<>();
    }

    @Override
    public void add(HPatient patient) {
        hPatientList.add(patient);
    }

    @Override
    public void remove(String id) throws PatientNotFoundException {
        hPatientList.remove(getByUserId(id));
    }

    @Override
    public HPatient getByUserId(String id) throws PatientNotFoundException {
        for (HPatient patient : hPatientList) {
            if (patient.getUserId().equalsIgnoreCase(id)) {
                return patient;
            }
        }

        throw new PatientNotFoundException("Could not find patient for ID " + id);
    }
}
