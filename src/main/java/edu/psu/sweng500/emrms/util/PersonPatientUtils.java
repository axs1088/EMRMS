package edu.psu.sweng500.emrms.util;

import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.HName;
import edu.psu.sweng500.emrms.model.HPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personPatientUtils")

public class PersonPatientUtils {
    @Autowired
    private PatientDemographicsMapper patientDemographicsMapper;

    public String getPatientName (int patientObjectID) {
        HPatient patient = patientDemographicsMapper.getPatientDetails(patientObjectID);
        int personId = patient.getPersonId();
        HName personName = patientDemographicsMapper.getPersonName(personId);
        String patientLastName = personName.getLastName();
        String patientFirstName = personName.getFirstName();
        String patientMiddleName = personName.getMiddleName();
        String patientName = patientLastName + ", " + patientFirstName + " " + patientMiddleName;
        return patientName;
    }

    public String getPersonName (int personId) {
        HName personName = patientDemographicsMapper.getPersonName(personId);
        String patientLastName = personName.getLastName();
        String patientFirstName = personName.getFirstName();
        String patientMiddleName = personName.getMiddleName();
        String patientName = patientLastName + ", " + patientFirstName + " " + patientMiddleName;
        return patientName;
    }
}
