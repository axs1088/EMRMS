package edu.psu.sweng500.emrms.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EMRMSPropertiesTest {
    @Before
    public void setUp() throws Exception {
        EMRMSProperties.loadFile("/forUnitTest.properties");
    }

    @Test
    public void get() throws Exception {
        Assert.assertNull(EMRMSProperties.get("NothingString"));

        assertEquals("Good", EMRMSProperties.get("testString"));
    }

    @Test
    public void getBoolean() throws Exception {
        Assert.assertFalse(EMRMSProperties.getBoolean("NothingBoolean"));
        Assert.assertTrue(EMRMSProperties.getBoolean("testBooleanTrue"));
        Assert.assertFalse(EMRMSProperties.getBoolean("testBooleanFalse"));
    }

    @Test
    public void getInt() throws Exception {
        Assert.assertNull(EMRMSProperties.getInt("NothingInt"));
        assertEquals(new Integer(42), EMRMSProperties.getInt("testInteger"));
    }
}