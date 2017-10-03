package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HPersonTest {
    private HPerson person;
    private String expectedString;
    private Date expectedDate;
    private int expectedLong;

    @Before
    public void testUp() {
        person = new HPerson();
        expectedDate = TestingUtilities.createRandomSqlDate();
        expectedString = TestingUtilities.createRandomString(5);
        expectedLong = (new Random()).nextInt();
    }

    @Test
    public void testPersonId() throws Exception {
        person.setPersonId(expectedLong);
        assertEquals(expectedLong, person.getPersonId());
    }

    @Test
    public void testUserId() throws Exception {
        person.setUserId(expectedString);
        assertEquals(expectedString, person.getUserId());
    }

    @Test
    public void testCreationDateTime() throws Exception {
        person.setCreationDateTime(expectedDate);
        assertEquals(expectedDate, person.getCreationDateTime());
    }

    @Test
    public void testGender() throws Exception {
        person.setGender(expectedLong);
        assertEquals(expectedLong, person.getGender());
    }

    @Test
    public void testBirthDate() throws Exception {
        person.setBirthDate(expectedDate);
        assertEquals(expectedDate, person.getBirthDate());
    }

    @Test
    public void testRace() throws Exception {
        person.setRace(expectedString);
        assertEquals(expectedString, person.getRace());
    }

}