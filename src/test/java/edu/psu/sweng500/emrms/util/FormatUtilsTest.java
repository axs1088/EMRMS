package edu.psu.sweng500.emrms.util;

import org.junit.Test;

import java.text.ParseException;
import static org.junit.Assert.assertEquals;

public class FormatUtilsTest {
    
    @Test
    public void formatDate() throws ParseException {
    	String expected = "2017-10-04";
    	String inputDate = "2017-10-04 00:00:00.0";
        assertEquals(expected, FormatUtils.formatDate(inputDate));
    }

}