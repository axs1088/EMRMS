package edu.psu.sweng500.emrms.util;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class FormatUtils {
    public static String unformatNumber(String number) {
        String result = null;
        if (number != null) {
            result = number.replaceAll("[^\\d]", "");
        }
        return result;
    }

    public static String formatPolicyNumber(String polNumber) {
        if (StringUtils.isNotBlank(polNumber)) {
            int policyNoMaxLength = 10;
            int countOfZero = policyNoMaxLength - polNumber.length();
            for (int i = 0; i < countOfZero; i++) {
                polNumber = "0" + polNumber;
            }
        }
        return polNumber;
    }

    public static String sortedMapString(Map<String, ?> map) {
        // Quick reject null.
        if (map == null) {
            return "null";
        }

        StringBuffer result = new StringBuffer();

        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);

        result.append('{');
        String separator = "";
        for (String key : keys) {
            result.append(separator);
            result.append(key);
            result.append('=');
            result.append(String.valueOf(map.get(key)));

            separator = ", ";
        }
        result.append('}');

        return result.toString();
    }

    public static String sortedSetString(Set<?> set) {
        // Quick reject null.
        if (set == null) {
            return "null";
        }

        List<String> items = new ArrayList<>();
        for (Object item : set) {
            items.add(String.valueOf(item));
        }
        Collections.sort(items);

        return '{' + StringUtils.join(items, ", ") + '}';
    }
}
