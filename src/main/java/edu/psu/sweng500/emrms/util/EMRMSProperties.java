package edu.psu.sweng500.emrms.util;

import org.apache.commons.lang.BooleanUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Preference properties access.
 */
public final class EMRMSProperties {

    /**
     * The instance.
     */
    private static volatile EMRMSProperties instance;
    private String propertiesFilename;

    /**
     * The properties.
     */
    private Properties properties;

    /**
     * Instantiates a new Preference properties.
     */
    public EMRMSProperties() {
        propertiesFilename = "";
        properties = null;
    }

    public void initialize(String propertiesFilename) throws IOException {
        this.propertiesFilename = propertiesFilename;

        try (FileInputStream input = new FileInputStream(propertiesFilename)) {
            properties = new Properties();
            properties.load(input);
        }
    }

    /**
     * Gets the single instance of PreferenceProperties.
     *
     * @return single instance of PreferenceProperties
     */

    public static EMRMSProperties getInstance() {
        if (instance == null) {
            instance = new EMRMSProperties();
        }

        return instance;
    }

    /**
     * Gets the keysSeparatedByComma for a key.
     *
     * @param key the key
     * @return the keysSeparatedByComma, or null if not found
     */
    public String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets the boolean keysSeparatedByComma for a key.
     *
     * @param key the key
     * @return the boolean keysSeparatedByComma, or false if not found
     */
    public Boolean getBoolean(String key) {
        String value = get(key);

        if (value == null) {
            return null;
        }

        boolean result = BooleanUtils.toBoolean(value);
        return result;
    }

    /**
     * Gets the integer keysSeparatedByComma for a key.
     *
     * @param key the key
     * @return the integer keysSeparatedByComma, or null if not found
     * @throws NumberFormatException if the keysSeparatedByComma cannot be parsed as an integer
     */
    public Integer getInt(String key) {
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
     * @param keysSeparatedByComma the keysSeparatedByComma
     * @return list of values
     */
    public List<String> getList(String keysSeparatedByComma) {
        List<String> valueList = new ArrayList<>();

        if (keysSeparatedByComma.isEmpty()) {
            return valueList;
        }

        List<String> keyList = Arrays.asList(keysSeparatedByComma.split(","));
        for (String key : keyList) {
            String value = get(key);
            if (value != null) {
                valueList.add(value);
            }
        }

        return valueList;
    }

}
