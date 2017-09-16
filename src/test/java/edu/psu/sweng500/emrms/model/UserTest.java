package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.util.TestingUtilities;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;
    private String expectedString;
    private long expectedLong;
    private boolean expectedBoolean;
    private Date expectedDate;

    @Before
    public void setUp() {
        user = new User();
        expectedString = TestingUtilities.createRandomString(5);
        expectedLong = (new Random()).nextLong();
        expectedBoolean = new Random().nextBoolean();
        expectedDate = TestingUtilities.createRandomSqlDate();
    }

    @Test
    public void testUserId() throws Exception {
        user.setUserId(expectedLong);
        assertEquals(expectedLong, user.getUserId());
    }

    @Test
    public void testCreationDateTime() throws Exception {
        user.setCreationDateTime(expectedDate);
        assertEquals(expectedDate, user.getCreationDateTime());
    }

    @Test
    public void testLoginId() throws Exception {
        user.setLoginId(expectedString);
        assertEquals(expectedString, user.getLoginId());
    }

    @Test
    public void testUserType() throws Exception {
        user.setUserType(expectedLong);
        assertEquals(expectedLong, user.getUserType());
    }

    @Test
    public void testhPersonId() throws Exception {
        user.sethPersonId(expectedLong);
        assertEquals(expectedLong, user.gethPersonId());
    }

    @Test
    public void testhPasswordId() throws Exception {
        user.sethPasswordId(expectedLong);
        assertEquals(expectedLong, user.gethPasswordId());
    }

    @Test
    public void testUsername() throws Exception {
        user.setUsername(expectedString);
        assertEquals(expectedString, user.getUsername());
    }

    @Test
    public void testPassword() throws Exception {
        user.setPassword(expectedString);
        assertEquals(expectedString, user.getPassword());
    }
}