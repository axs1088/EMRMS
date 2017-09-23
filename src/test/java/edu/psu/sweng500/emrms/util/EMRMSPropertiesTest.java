package edu.psu.sweng500.emrms.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EMRMSPropertiesTest {
    EMRMSProperties properties;

    @Before
    public void setUp() throws Exception {
        properties = EMRMSProperties.getInstance();
        properties.initialize("src/main/resources/forUnitTest.properties");
    }

    @Test
    public void get() throws Exception {
        Assert.assertNull(properties.get("NothingString"));

        assertEquals("Good", properties.get("testString"));
    }

    @Test
    public void getBoolean() throws Exception {
        Assert.assertNull(properties.getBoolean("NothingBoolean"));
        Assert.assertTrue(properties.getBoolean("testBooleanTrue"));
        Assert.assertFalse(properties.getBoolean("testBooleanFalse"));
    }

    @Test
    public void getInt() throws Exception {
        Assert.assertNull(properties.getInt("NothingInt"));
        assertEquals(new Integer(42), properties.getInt("testInteger"));
    }

    @Test
    public void getList() throws Exception {
        List<String> valueList = properties.getList("");
        assertTrue(valueList.isEmpty());

        valueList = properties.getList("Nothing1,Nothing2");
        assertTrue(valueList.isEmpty());

        valueList = properties.getList("testList1,testList2,testList3");
        assertEquals(3, valueList.size());
        assertEquals("1", valueList.get(0));
        assertEquals("2", valueList.get(1));
        assertEquals("3", valueList.get(2));
    }

}