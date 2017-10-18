package edu.psu.sweng500.emrms.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.util.EMRMSProperties;

@Component
public class PatientValidator {
	
	public static final String nameRegEx = new String ("^[\\p{L} .']+$");
	public static final String phoneNoRegEx = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	public static final String emailRegEx = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	public static final String zipCodeRegEx = "^[0-9]{5}(?:-[0-9]{4})?$";
	public static final String streetAddressRegEx = "^[#.0-9a-zA-Z\\s,-]+$";

	public List<String> validate(HPatient patient) {
		List<String> errors = new ArrayList<String>();
		
		if(!validate(patient.getName().getFirst(), nameRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.firstname"));
        
        if(!validate(patient.getName().getLast(), nameRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.lastname"));
        
        if(!validate(patient.getName().getMiddle(), nameRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.middlename"));
        
        if(!validate(patient.getCellPhone().getNumber(), phoneNoRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.cellphone"));
        
        if(!validate(patient.getHomePhone().getNumber(), phoneNoRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.homephone"));
        
        if(!validate(patient.getEmail(), emailRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.email"));
        
        if(!validate(patient.getAddress().getZip(), zipCodeRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.zipcode"));
        
        if(!validate(patient.getAddress().getLine1(), streetAddressRegEx))
        	errors.add(EMRMSProperties.get("patientregistration.validate.streetaddress"));
        
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
