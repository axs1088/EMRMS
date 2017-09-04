package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.exceptions.PatientNotFoundException;
import edu.psu.sweng500.emrms.models.HPatient;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class DatabasePatientControllerTest extends TestCase {

    PatientController patientController;
    final String existingPatientId = "ActualPerson";
    final String notExistingPatientId = "NotReal";

    @Before
    public void setUp() {
        patientController = new DatabasePatientController();
    }

    void addExistingPatient() {
        HPatient existingPatient = new HPatient();
        existingPatient.setUserId(existingPatientId);
        patientController.add(existingPatient);
    }

    @Test
    public void testAddAndGetValidPatient() {
        addExistingPatient();

        try {
            patientController.getByUserId(existingPatientId);
        } catch (PatientNotFoundException e) {
            fail("Expected to find patient with ID " + existingPatientId);
        }
    }


    @Test
    public void testGetByUserIdFailure() {
        addExistingPatient();

        try {
            patientController.getByUserId(notExistingPatientId);
        } catch (PatientNotFoundException e) {
            // expected, we passed
            return;
        }

        fail("Expected to not find patient with id=" + notExistingPatientId);
    }

    @Test
    public void testRemovePatient() {
        addExistingPatient();
        try {
            patientController.remove(existingPatientId);
        } catch (PatientNotFoundException e) {
            fail("Expected to find patient, id=" + existingPatientId);
        }

        try {
            patientController.remove(existingPatientId);
        } catch (PatientNotFoundException e) {
            // expected, we passed
            return;
        }

        fail("Patient was not properly removed, id=" + existingPatientId);
    }
}