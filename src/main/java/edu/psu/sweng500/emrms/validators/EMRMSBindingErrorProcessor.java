package edu.psu.sweng500.emrms.validators;

import org.springframework.beans.PropertyAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;

import edu.psu.sweng500.emrms.format.DateFormatterException;

public class EMRMSBindingErrorProcessor extends DefaultBindingErrorProcessor {
    @Override
    public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
        Throwable cause = ex.getMostSpecificCause();
        if(cause instanceof DateFormatterException) {
        	DateFormatterException dfe = (DateFormatterException)cause;
    		String field = ex.getPropertyName();
    		String[] codes = bindingResult.resolveMessageCodes(dfe.getCode(), field);
    		Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), field);
    		Object rejectedValue = ex.getValue();
            FieldError fieldError =
                    new FieldError(bindingResult.getObjectName(), field, rejectedValue, true, codes, arguments, dfe.getLocalizedMessage());
    		bindingResult.addError(fieldError);
        } else {
        	super.processPropertyAccessException(ex, bindingResult);
        }
    }
}
