package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HPatientTest {
    private HPatient patient;
    private String expectedString;
    private long expectedLong;
    private boolean expectedBoolean;

    @Before
    public void setUp() {
        patient = new HPatient();
        expectedString = TestingUtilities.createRandomString(5);
        expectedLong = (new Random()).nextLong();
        expectedBoolean = new Random().nextBoolean();
    }

    @Test
    public void testObjectID() throws Exception {
        patient.setObjectID(expectedLong);
        assertEquals(expectedLong, patient.getObjectID());
    }

    @Test
    public void testUserId() throws Exception {
        patient.setUserId(expectedString);
        assertEquals(expectedString, patient.getUserId());
    }

    @Test
    public void testCreationDateTime() throws Exception {
        patient.setCreationDateTime(expectedString);
        assertEquals(expectedString, patient.getCreationDateTime());
    }

    @Test
    public void testmPINumber() throws Exception {
        patient.setmPINumber(expectedString);
        assertEquals(expectedString, patient.getmPINumber());
    }

    @Test
    public void testOrganDonor() throws Exception {
        patient.setOrganDonor(expectedBoolean);
        assertEquals(expectedBoolean, patient.getOrganDonor());
    }

    @Test
    public void testDeceasedIndicator() throws Exception {
        patient.setDeceasedIndicator(expectedBoolean);
        assertEquals(expectedBoolean, patient.getDeceasedIndicator());
    }

    @Test
    public void testPatientUndentified() throws Exception {
        patient.setPatientUndentified(expectedBoolean);
        assertEquals(expectedBoolean, patient.getPatientUndentified());
    }

    @Test
    public void testPrimarylang() throws Exception {
        patient.setPrimarylang(expectedString);
        assertEquals(expectedString, patient.getPrimarylang());
    }

    @Test
    public void testMedHistoryConsent() throws Exception {
        patient.setMedHistoryConsent(expectedBoolean);
        assertEquals(expectedBoolean, patient.getMedHistoryConsent());
    }
}