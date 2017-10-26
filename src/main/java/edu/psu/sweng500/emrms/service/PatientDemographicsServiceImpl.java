package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("getPatientDemographicsService")
public class PatientDemographicsServiceImpl implements PatientDemographicsService {

    @Autowired
    private
    PatientDemographicsMapper patientDemographicsMapper;


    public void setPatientDemographicsMapper(PatientDemographicsMapper patientDemographicsMapper) {
        this.patientDemographicsMapper = patientDemographicsMapper;
    }

    @Override
    public int getPersonId(int patientObjectId) {
        HPatient patient = patientDemographicsMapper.getPatientDetails(patientObjectId);
        int personId = patient.getPersonId();
        return personId;
    }

    @Override
    public HPatient getPatientDemographics(int patientObjectId) {
        HPatient patient = patientDemographicsMapper.getPatientDetails(patientObjectId);
        return patient;
    }

    @Override
    public HPerson getPersonDetails(int personId) {
        HPerson person = patientDemographicsMapper.getPersonDetails(personId);
        return person;
    }

    @Override
    public HName getPersonName(int personId) {
        HName personName = patientDemographicsMapper.getPersonName(personId);
        return personName;
    }

    @Override
    public Address getPersonAddress(int personId) {
        Address personAddress = patientDemographicsMapper.getPersonAddress(personId);
        return personAddress;
    }
    
    @Override
    public Phone getHomePhone(int personId) {
        Phone homePhone = patientDemographicsMapper.getHomePhone(personId);
        return homePhone;
    }
    
    @Override
    public Phone getCellPhone(int personId) {
        Phone cellPhone = patientDemographicsMapper.getCellPhone(personId);
        return cellPhone;
    }
    
    @Override
    public String getEmail(int personId) {
        String email = patientDemographicsMapper.getEmail(personId);
        return email;
    }

    @Override
    public List<HPatientId> getPatientIdentifiers(int patientObjectId) {
        List<HPatientId> patientIds = patientDemographicsMapper.getPatientIdentifiers(patientObjectId);
        return patientIds;
    }

    @Override
    public List<HEncounter> getPatientEncounters(int patientObjectId) {
        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientObjectId);
        return encounters;
    }

    @Override
    public List<HAllergy> getPatientAllergies(int patientObjectId) {
        List<HAllergy> allergies = patientDemographicsMapper.getPatientAllergies(patientObjectId);
        return allergies;
    }

    @Override
    public List<HDiagnosis> getPatientDiagnoses(int patientObjectId) {
        List<HDiagnosis> diagnoses = patientDemographicsMapper.getPatientDiagnoses(patientObjectId);
        return diagnoses;
    }


     /*
    @Override
    public void getPatientDemographics(int patientObjectId)
    {
        HPatient patient =  patientDemographicsMapper.getPatientDetails(patientObjectId);
        int personId = patient.getPersonId();
        HPerson person = patientDemographicsMapper.getPersonDetails(personId);
        HName personName = patientDemographicsMapper.getPersonName(personId);
        Address personAddress = patientDemographicsMapper.getPersonAddress(personId);
        List<HPatientId> patientIds = patientDemographicsMapper.getPatientIdentifiers(patientObjectId);
        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientObjectId);

    }
    */
}
