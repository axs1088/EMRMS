package edu.psu.sweng500.emrms.util;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FormatUtilsTest {
    @Test
    public void formatPolicyNumber() throws Exception {
        String input = "1";
        String expected = "0000000001";

        assertEquals(expected, FormatUtils.formatPolicyNumber(input));
    }

    @Test
    public void sortedMapString() throws Exception {
        String expected = "{a=1, b=2, c=3}";

        HashMap<String, String> input = new HashMap<>();
        input.put("b", "2");
        input.put("a", "1");
        input.put("c", "3");

        assertEquals(expected, FormatUtils.sortedMapString(input));

        assertEquals("null", FormatUtils.sortedMapString(null));
    }

    @Test
    public void sortedSetString() throws Exception {
    }

}