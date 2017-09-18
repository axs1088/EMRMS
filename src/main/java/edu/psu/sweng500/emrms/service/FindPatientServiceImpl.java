package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.FindPatientMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("findPatientByDemogrpahicsService")
public class FindPatientServiceImpl implements FindPatientService{
    @Autowired

    private FindPatientMapper findPatientMapper;

    public void setFindPatientMapper(FindPatientMapper findPatientMapper) {
        this.findPatientMapper = findPatientMapper;
    }

    public List<HCensus> getPatientListByDemogrpahics(String lName, String fName, Integer gender) {
        return findPatientMapper.getPatientListByDemogrpahics(lName,fName,gender);
    }
}

