package edu.psu.sweng500.emrms.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import edu.psu.sweng500.emrms.validators.annotations.DigitCount;



public class DigitCountValidator implements ConstraintValidator<DigitCount, CharSequence> {

	int requiredDigits;

	@Override
	public void initialize(DigitCount constraint) {
		this.requiredDigits = constraint.value();
	}

	@Override
	public boolean isValid(CharSequence valueObj, ConstraintValidatorContext context) {
		String value = null;
		if(valueObj != null) {
			value = valueObj.toString();
		}

		boolean result = true;
		if(!StringUtils.isEmpty(value)) {
			int digitCount = 0;
			for(int i = 0; i < value.length(); i++) {
				char ch = value.charAt(i);
				if(Character.isDigit(ch)) {
					digitCount++;
				} else if(Character.isLetter(ch)) {
					// Don't allow any letters.
					result = false;
					break;
				}
				// Ignore any other symbols, punctuation, etc.
			}
			if(digitCount != requiredDigits) {
				result = false;
			}
		}
		return result;
	}
}
