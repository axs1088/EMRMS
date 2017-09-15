package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HPatientIdTest {
    private HPatientId patientId;

    private String expectedString;
    private long expectedLong;

    @Before
    public void setUp() {
        patientId = new HPatientId();
        expectedString = TestingUtilities.createRandomString(5);
        expectedLong = (new Random()).nextLong();
    }

    @Test
    public void testhPatientId() throws Exception {
        patientId.sethPatientId(expectedLong);
        assertEquals(expectedLong, patientId.gethPatientId());
    }

    @Test
    public void testUserId() throws Exception {
        patientId.setUserId(expectedString);
        assertEquals(expectedString, patientId.getUserId());
    }

    @Test
    public void testCreationDateTime() throws Exception {
        patientId.setCreationDateTime(expectedString);
        assertEquals(expectedString, patientId.getCreationDateTime());
    }

    @Test
    public void testIdValue() throws Exception {
        patientId.setIdValue(expectedString);
        assertEquals(expectedString, patientId.getIdValue());
    }

    @Test
    public void testIdType() throws Exception {
        patientId.setIdType(expectedString);
        assertEquals(expectedString, patientId.getIdType());
    }

    @Test
    public void testIdIssuerName() throws Exception {
        patientId.setIdIssuerName(expectedString);
        assertEquals(expectedString, patientId.getIdIssuerName());
    }

    @Test
    public void testIdIssuerId() throws Exception {
        patientId.setIdIssuerId(expectedLong);
        assertEquals(expectedLong, patientId.getIdIssuerId());
    }

    @Test
    public void testPatientId() throws Exception {
        patientId.setPatientId(expectedLong);
        assertEquals(expectedLong, patientId.getPatientId());
    }
}