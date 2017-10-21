package edu.psu.sweng500.emrms.util;

import org.apache.commons.lang.BooleanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * EMRMS properties access.
 */
public class EMRMSProperties {

    /**
     * The properties.
     */
    private static Properties properties;

    private EMRMSProperties() {
        // Should not be initialized
    }

    // Package private to allow overload via unit testing
    static void loadFile(String fileName) {
        // Open the properties file.
        InputStream input = EMRMSProperties.class.getResourceAsStream(fileName);
        if (input == null) {
            throw new RuntimeException("Could not open file " + fileName);
        }

        // Parse it.
        properties = new Properties();
        try {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close " + fileName, e);
            }
        }

    }

    /**
     * Gets the single instance of EMRMSProperties.
     *
     * @return single instance of EMRMSProperties
     */
    private static Properties getInstance() {
        if (properties == null) {
            loadFile("/emrms.properties");
        }
        return properties;
    }

    /**
     * Gets the value for a key.
     *
     * @param key the key
     * @return the value, or null if not found
     */
    public static String get(String key) {
        return getInstance().getProperty(key);
    }

    /**
     * Gets the boolean value for a key.
     *
     * @param key the key
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
     * @param key the key
     * @return the integer value, or null if not found
     * @throws NumberFormatException if the value cannot be parsed as an integer
     */
    public static Integer getInt(String key) {
        Integer result = null;
        String value = get(key);
        if (value != null) {
            result = Integer.valueOf(value);
        }
        return result;
    }
}
