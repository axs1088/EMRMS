package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HCensusTest {
    private HCensus census;
    private String expectedString;
    private Date expectedDate;
    private long expectedLong;


    @Before
    public void setUp() {
        census = new HCensus();
        expectedDate = TestingUtilities.createRandomSqlDate();
        expectedString = TestingUtilities.createRandomString(5);
        expectedLong = (new Random()).nextLong();
    }

    @Test
    public void testFirstName() {
        census.setFirstName(expectedString);
        assertEquals(expectedString, census.getFirstName());
    }

    @Test
    public void testLastName() {
        census.setLastName(expectedString);
        assertEquals(expectedString, census.getLastName());
    }

    @Test
    public void testBirthdate() {
        census.setBirthdate(expectedDate);
        assertEquals(expectedDate, census.getBirthdate());
    }

    @Test
    public void testGender() {
        census.setGender(expectedLong);
        assertEquals(expectedLong, census.getGender());

    }

    @Test
    public void testMpiNo() {
        census.setmPINo(expectedString);
        assertEquals(expectedString, census.getmPINo());
    }

    @Test
    public void testEncStartDateTime() {
        census.setEncStartdateTime(expectedDate);
        assertEquals(expectedDate, census.getEncStartdateTime());
    }

    @Test
    public void testEncStatus() {
        census.setEncStatus(expectedString);
        assertEquals(expectedString, census.getEncStatus());
    }
}