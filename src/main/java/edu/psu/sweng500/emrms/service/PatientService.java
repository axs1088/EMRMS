package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HPatient;

import java.util.List;

public interface PatientService {
    List<HPatient> readAll();
}
