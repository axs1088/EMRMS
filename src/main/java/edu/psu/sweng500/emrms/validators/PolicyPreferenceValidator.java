package edu.psu.sweng500.emrms.validators;

import java.util.Set;

import org.springframework.validation.Errors;

import com.pennmutual.preference.controllers.PolicyPreferenceController;
import com.pennmutual.preference.models.PolicyPrefModel;

public class PolicyPreferenceValidator extends AbstractBaseValidator {
	
	private PolicyPreferenceController policyPreferenceController;
	
	public PolicyPreferenceValidator(PolicyPreferenceController pprefcon) {
		super(pprefcon);
		this.policyPreferenceController = pprefcon;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PolicyPrefModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	    PolicyPrefModel billing = (PolicyPrefModel)target;
		if(billing != null) {
			Set<String> requiredPaths = getRequiredPathsForController(policyPreferenceController);
			validateRequiredFields(requiredPaths, target, errors);
        }
	}	
}
