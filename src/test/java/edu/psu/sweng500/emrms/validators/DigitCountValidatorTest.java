package edu.psu.sweng500.emrms.validators;

import edu.psu.sweng500.emrms.validators.annotations.DigitCount;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DigitCountValidatorTest {
    @Test
    public void testIsValid() throws Exception {
        DigitCount fiveNumbers = mock(DigitCount.class);
        when(fiveNumbers.value()).thenReturn(5);
        DigitCountValidator validator = new DigitCountValidator();
        validator.initialize(fiveNumbers);

        assertFalse(validator.isValid("1", null));
        assertFalse(validator.isValid("1234567890", null));
        assertFalse(validator.isValid("abcde", null));

        assertTrue(validator.isValid("12345", null));
    }

}