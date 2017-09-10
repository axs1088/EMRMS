package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.exceptions.PatientNotFoundException;
import edu.psu.sweng500.emrms.model.HPatient;

public interface PatientController {
    public void add(HPatient patient);

    public void remove(String id) throws PatientNotFoundException;

    public HPatient getByUserId(String id) throws PatientNotFoundException;
}
