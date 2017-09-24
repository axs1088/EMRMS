package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HEncounterTest {
    private HEncounter encounter;
    private String expectedString;
    private Date expectedDate;
    private int expectedInt;

    @Before
    public void setUp() {
        encounter = new HEncounter();
        expectedDate = TestingUtilities.createRandomSqlDate();
        expectedString = TestingUtilities.createRandomString(6);
        expectedInt = (new Random()).nextInt();
    }

    @Test
    public void getPatient_ObjectID() throws Exception {
        encounter.setPatient_ObjectID(expectedInt);
        assertEquals(expectedInt, encounter.getPatient_ObjectID());
    }

    @Test
    public void getAttendingPhysician_ObjectID() throws Exception {
        encounter.setAttendingPhysician_ObjectID(expectedInt);
        assertEquals(expectedInt, encounter.getAttendingPhysician_ObjectID());
    }

    @Test
    public void getBed_ObjectID() throws Exception {
        encounter.setBed_ObjectID(expectedInt);
        assertEquals(expectedInt, encounter.getBed_ObjectID());
    }

    @Test
    public void gethEncounterID() throws Exception {
        encounter.sethEncounterID(expectedInt);
        assertEquals(expectedInt, encounter.gethEncounterID());
    }

    @Test
    public void getUserID() throws Exception {
        encounter.setUserID(expectedString);
        assertEquals(expectedString, encounter.getUserID());
    }

    @Test
    public void getCreationDateTime() throws Exception {
        encounter.setCreationDateTime(expectedString);
        assertEquals(expectedString, encounter.getCreationDateTime());
    }

    @Test
    public void getEncStartDateTime() throws Exception {
        encounter.setEncStartDateTime(expectedString);
        assertEquals(expectedString, encounter.getEncStartDateTime());
    }

    @Test
    public void getEncEndDateTime() throws Exception {
        encounter.setEncEndDateTime(expectedString);
        assertEquals(expectedString, encounter.getEncEndDateTime());
    }

    @Test
    public void getEncStatus() throws Exception {
        encounter.setEncStatus(expectedInt);
        assertEquals(expectedInt, encounter.getEncStatus());
    }

    @Test
    public void getEncLocationName() throws Exception {
        encounter.setEncLocationName(expectedString);
        assertEquals(expectedString, encounter.getEncLocationName());
    }

    @Test
    public void getEncounterLocation_ObjectID() throws Exception {
        encounter.setEncounterLocation_ObjectID(expectedInt);
        assertEquals(expectedInt, encounter.getEncounterLocation_ObjectID());
    }

    @Test
    public void getEncounterID() throws Exception {
        encounter.setEncounterID(expectedString);
        assertEquals(expectedString, encounter.getEncounterID());
    }

    @Test
    public void getEncounterType() throws Exception {
        encounter.setEncounterType(expectedString);
        assertEquals(expectedString, encounter.getEncounterType());
    }

    @Test
    public void getBedName() throws Exception {
        encounter.setBedName(expectedString);
        assertEquals(expectedString, encounter.getBedName());
    }

}