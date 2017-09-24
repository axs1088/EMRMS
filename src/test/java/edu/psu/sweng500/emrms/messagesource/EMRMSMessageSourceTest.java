package edu.psu.sweng500.emrms.messagesource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.FieldError;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EMRMSMessageSourceTest {
    EMRMSMessageSource messageSource;
    String[] codes;
    Integer[] arguments;
    Integer rejectedValue;
    FieldError fieldError;
    Locale locale;

    @Before
    public void setUp() throws Exception {
        messageSource = new EMRMSMessageSource();
        codes = new String[]{"1", "2", "3"};
        arguments = new Integer[]{555, 666, 777};
        rejectedValue = 42;
        fieldError = new FieldError("testObjectName", "testField", rejectedValue, true, codes, arguments, "testMessage");
        locale = Locale.US;

    }

    @Test
    public void testFieldError() throws Exception {
        assertEquals("testMessage", messageSource.getMessage(fieldError, locale));

        try {
            messageSource.getMessage(codes[0], arguments, locale);
            fail();
        } catch (NoSuchMessageException ex) {
            // pass
        }

        assertEquals("defaultMessage", messageSource.getMessage(codes[0], arguments, "defaultMessage", locale));

        String label = messageSource.getLabel("path", "prefix", fieldError, locale);
    }
}