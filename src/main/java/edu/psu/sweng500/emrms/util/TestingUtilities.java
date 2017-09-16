package edu.psu.sweng500.emrms.util;

import java.sql.Date;
import java.util.Random;

public class TestingUtilities {
    public static String createRandomString(int stringLength) {
        String alphaNumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        while (builder.length() < stringLength) { // length of the random string.
            int index = Math.abs(random.nextInt()) % alphaNumericCharacters.length();
            builder.append(alphaNumericCharacters.charAt(index));
        }

        return builder.toString();
    }

    public static Date createRandomSqlDate() {
        return new Date(Math.abs(new Random().nextLong()));
    }
}
