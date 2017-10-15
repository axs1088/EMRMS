package edu.psu.sweng500.emrms.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexNameTest {
    ComplexName blankName;
    ComplexName realName;
    ComplexName alsoRealName;
    String expectedRealNameString;

    @Before
    public void setUp() {
        blankName = new ComplexName();

        realName = new ComplexName();

        expectedRealNameString = "Thisis Areal Name";

        String[] realNameSplit = expectedRealNameString.split(" ");
        realName.setFirst(realNameSplit[0]);
        realName.setMiddle(realNameSplit[1]);
        realName.setLast(realNameSplit[2]);

        alsoRealName = new ComplexName();
        alsoRealName.setFirst(realNameSplit[0]);
        alsoRealName.setMiddle(realNameSplit[1]);
        alsoRealName.setLast(realNameSplit[2]);
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(blankName.toString().isEmpty());
        assertEquals(expectedRealNameString, realName.toString());
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(blankName.isEmpty());
    }

    @Test
    public void testHashCode() throws Exception {
        assertTrue(realName.hashCode() > 0);
    }

    @Test
    public void testEquals() throws Exception {
        ComplexName differentRealName = new ComplexName();
        differentRealName.setFirst("John");
        Object notAName = "string!";
        ComplexName nullName = new ComplexName();

        assertTrue(realName.equals(realName));
        assertFalse(realName.equals(null));
        assertFalse(realName.equals(notAName));
        assertFalse(realName.equals(nullName));
        assertFalse(nullName.equals(realName));
        assertTrue(realName.equals(alsoRealName));
        assertFalse(realName.equals(differentRealName));

        ComplexName name1 = new ComplexName();
        ComplexName name2 = new ComplexName();

        name1.setFirst("first");
        assertFalse(name1.equals(name2));
        assertFalse(name2.equals(name1));
        name2.setFirst("first");
        assertTrue(name1.equals(name2));

        name1.setLast("last");
        assertFalse(name1.equals(name2));
        assertFalse(name2.equals(name1));
        name2.setLast("last");
        assertTrue(name1.equals(name2));

        name1.setMiddle("middle");
        assertFalse(name1.equals(name2));
        assertFalse(name2.equals(name1));
        name2.setMiddle("middle");
        assertTrue(name1.equals(name2));

        name1.setPrefix("Jr");
        assertFalse(name1.equals(name2));
        assertFalse(name2.equals(name1));
        name2.setPrefix("Jr");
        assertTrue(name1.equals(name2));
    }


}