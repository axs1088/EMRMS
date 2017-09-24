package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.CensusMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("censusService")
public class CensusServiceImpl implements CensusService {
    @Autowired
    private CensusMapper censusMapper;

    public void setCensusMapper(CensusMapper censusMapper) {
        this.censusMapper = censusMapper;
    }

    @Override
    public List<HCensus> getPhysicianCensus(Integer userObjectID) {
        return censusMapper.getPhysicianCensus(userObjectID);
    }

    @Override
    public List<HCensus> getNurseCensus(Integer locationObjectID) {
        return censusMapper.getNurseCensus(locationObjectID);
    }

    @Override
    public List<HCensus> getPatientListByDemographics(String lName, String fName, Integer gender) {
        return censusMapper.getPatientListByDemographics(lName, fName, gender);
    }
}
