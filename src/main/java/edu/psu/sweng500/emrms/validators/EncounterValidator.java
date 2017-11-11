package edu.psu.sweng500.emrms.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.util.EMRMSProperties;

@Component
public class EncounterValidator {
	
	public static final String nameRegEx = new String ("^[\\p{L} .']+$");
	public static final String chiefComplaintRegEx = new String ("^[0-9\\p{L} .']+$");
	public static final String encounterIdRegEx = "^[0-9a-zA-Z\\s]+$";

	public List<String> validate(HEncounter encounter) {
		List<String> errors = new ArrayList<String>();
		
		if(!validate(encounter.getEncounterID(), encounterIdRegEx))
        	errors.add(EMRMSProperties.get("patientencounter.validate.encounterid"));
        
        if(!validate(encounter.getEncounterReason(), chiefComplaintRegEx))
        	errors.add(EMRMSProperties.get("patientencounter.validate.encounterreason"));
        
        if(!validate(encounter.getAttendingPhysician(), nameRegEx))
        	errors.add(EMRMSProperties.get("patientencounter.validate.physicianname"));
        
        return errors;
        
	}
	
	private boolean validate(String input, String regEx){
		if(StringUtils.isNotBlank(input)) {
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(input);
	        if(!matcher.matches())
	        {
	        	return false;
	        }
		}
		return true;
	}
	
}
