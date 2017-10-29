package edu.psu.sweng500.emrms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatUtils {
    
    public static String formatDate(String date) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return format.format(inputFormat.parse(date));
    }
    
}
