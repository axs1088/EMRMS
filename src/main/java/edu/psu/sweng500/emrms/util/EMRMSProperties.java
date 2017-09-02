package edu.psu.sweng500.emrms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Preference properties access.
 */
public final class EMRMSProperties {

    /** The instance. */
    private static volatile EMRMSProperties instance;

    /** The properties. */
    private Properties properties;

    /**
     * Instantiates a new Preference properties.
     */
    private EMRMSProperties() {
        // Open the properties file.
        InputStream input = getClass().getResourceAsStream("/preference.properties");
        if (input == null) {
            throw new RuntimeException("Missing preference.properties");
        }

        // Parse it.
        properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load preference.properties", e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close preference.properties", e);
            }
        }

    }

    /**
     * Gets the single instance of PreferenceProperties.
     * 
     * @return single instance of PreferenceProperties
     */
    private static EMRMSProperties getInstance() {
        if (instance == null) {
            instance = new EMRMSProperties();
        }
        return instance;
    }

    /**
     * Gets the value for a key.
     * 
     * @param key
     *            the key
     * @return the value, or null if not found
     */
    public static String get(String key) {
        return getInstance().properties.getProperty(key);
    }

    /**
     * Gets the boolean value for a key.
     * 
     * @param key
     *            the key
     * @return the boolean value, or false if not found
     */
    public static boolean getBoolean(String key) {
        String value = get(key);
        boolean result = BooleanUtils.toBoolean(value);
        return result;
    }

    /**
     * Gets the integer value for a key.
     * 
     * @param key
     *            the key
     * @return the integer value, or null if not found
     * @throws NumberFormatException
     *             if the value cannot be parsed as an integer
     */
    public static Integer getInt(String key) {
        Integer result = null;
        String value = get(key);
        if (value != null) {
            result = Integer.valueOf(value);
        }
        return result;
    }

    /**
     * Gets the list of values.
     * 
     * @param value
     *            the value
     * @return list of values
     */
    public static List<String> getList(String value) {
        List<String> values = new ArrayList<String>();
        if (StringUtils.isNotBlank(value)) {
            values.addAll(Arrays.asList(value.split(",")));
        }
        return values;
    }

}
