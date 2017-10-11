package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("getPatientDemographics")
public class GetPatientDemographicsServiceImpl implements GetPatientDemographicsService {

    @Autowired
    PatientDemographicsMapper patientDemographicsMapper;

    @Override
    public int getPersonId(int patientObjectId){
        HPatient patient =  patientDemographicsMapper.getPatientDetails(patientObjectId);
        int personId = patient.getPersonId();
        return personId;
    }

    @Override
    public HPatient getPatientDemographics(int patientObjectId){
        HPatient patient =  patientDemographicsMapper.getPatientDetails(patientObjectId);
        return patient;
    }

    @Override
    public HPerson getPersonDetails(int personId){
        HPerson person = patientDemographicsMapper.getPersonDetails(personId);
        return  person;
    }

    @Override
    public HName getPersonName(int personId){
        HName personName = patientDemographicsMapper.getPersonName(personId);
        return personName;
    }

    @Override
    public Address getPersonAddress(int personId){
        Address personAddress = patientDemographicsMapper.getPersonAddress(personId);
        return personAddress;
    }

    @Override
    public List<HPatientId> getPatientIdentifiers(int patientObjectId){
        List<HPatientId> patientIds = patientDemographicsMapper.getPatientIdentifiers(patientObjectId);
        return patientIds;
    }

    @Override
    public List<HEncounter> getPatientEncounters(int patientObjectId){
        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientObjectId);
        return encounters;
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
