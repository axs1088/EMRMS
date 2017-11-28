package edu.psu.sweng500.emrms.util;

import edu.psu.sweng500.emrms.model.Address;
import edu.psu.sweng500.emrms.model.ComplexName;
import edu.psu.sweng500.emrms.model.HPatientId;
import edu.psu.sweng500.emrms.model.Phone;

import java.sql.Date;
import java.util.Random;

public class TestingUtilities {
    public static String createRandomString() {
        return createRandomString(6);
    }

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

    public static Object createRandomObjectFromClass(Class<?> returnType) throws ClassNotFoundException {
        Random random = new Random();

        if (returnType.equals(Date.class)) {
            return createRandomSqlDate();
        } else if (returnType.equals(String.class)) {
            return createRandomString(6);
        } else if (returnType.equals(Integer.class) || returnType.equals(Integer.TYPE)) {
            return random.nextInt();
        } else if (returnType.equals(Boolean.class) || returnType.equals(Boolean.TYPE)) {
            return random.nextBoolean();
        } else if (returnType.equals(Long.class) || returnType.equals(Long.TYPE)) {
            return random.nextLong();
        } else if (returnType.equals(RatifiedDate.class)) {
            return new RatifiedDate(random.nextLong());
        } else if (returnType.equals(ComplexName.class)) {
            ComplexName complexName = new ComplexName();
            complexName.setFirst(createRandomString());
            complexName.setMiddle(createRandomString());
            complexName.setLast(createRandomString());
            return complexName;
        } else if (returnType.equals(Address.class)) {
            Address address = new Address();
            address.setCity(createRandomString());
            address.setCountry(createRandomString());
            return address;
        } else if (returnType.equals(Phone.class)) {
            return new Phone(createRandomString());
        }else if (returnType.equals(HPatientId.class)) {
            return new HPatientId();
        }

        throw new ClassNotFoundException("Could not create object of type: " + returnType.getName());
    }
}
