package edu.psu.sweng500.emrms.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneTest {
    Phone allFives;
    String allFivesString;

    @Before
    public void setUp() {
        allFivesString = "5555555555";
        allFives = new Phone(allFivesString);
    }

    @Test
    public void testHashCode() throws Exception {
        assertTrue(allFives.hashCode() > 0);
    }

    @Test
    public void testEquals() throws Exception {
        Phone alsoAllFives = new Phone("5555555555");
        Phone allFours = new Phone("4444444444");
        Object notAPhone = "string!";
        Phone nullNumberPhone = new Phone();

        assertTrue(allFives.equals(allFives));
        assertFalse(allFives.equals(null));
        assertFalse(allFives.equals(notAPhone));
        assertFalse(allFives.equals(nullNumberPhone));
        assertFalse(nullNumberPhone.equals(allFives));
        assertTrue(allFives.equals(alsoAllFives));
        assertFalse(allFives.equals(allFours));
    }

    @Test
    public void testIsEmpty() throws Exception {
        Phone emptyPhone = new Phone("");

        assertTrue(emptyPhone.isEmpty());
        assertFalse(allFives.isEmpty());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(allFivesString, allFives.toString());
    }
}