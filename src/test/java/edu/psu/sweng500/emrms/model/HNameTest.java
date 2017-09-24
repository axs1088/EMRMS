package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HNameTest {
    HName name;
    long randomLong;
    private String randomString;

    @Before
    public void setUp() {
        name = new HName();
        randomLong = (new Random()).nextLong();
        randomString = TestingUtilities.createRandomString(8);
    }

    @Test
    public void getObjectID() throws Exception {
        name.setObjectID(randomLong);
        assertEquals(randomLong, name.getObjectID());
    }

    @Test
    public void getUserId() throws Exception {
        name.setUserId(randomString);
        assertEquals(randomString, name.getUserId());
    }

    @Test
    public void getCreationDateTime() throws Exception {
        name.setCreationDateTime(randomString);
        assertEquals(randomString, name.getCreationDateTime());
    }

    @Test
    public void getLastName() throws Exception {
        name.setLastName(randomString);
        assertEquals(randomString, name.getLastName());
    }

    @Test
    public void getFirstName() throws Exception {
        name.setFirstName(randomString);
        assertEquals(randomString, name.getFirstName());
    }

    @Test
    public void getActive() throws Exception {
        name.setActive(false);
        assertFalse(name.getActive());
    }
}