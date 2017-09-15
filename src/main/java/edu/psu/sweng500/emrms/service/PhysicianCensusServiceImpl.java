package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.mappers.PhysicianCensusMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("physicianCensusService")
public class PhysicianCensusServiceImpl implements PhysicianCensusService {
    @Autowired
    private PhysicianCensusMapper censusMapper;

    public void setCensusMapper(PhysicianCensusMapper censusMapper) {
        this.censusMapper = censusMapper;
    }

    public List<HCensus> getPhysicianCensus(Integer userObjectID) {
        return censusMapper.getPhysicianCensus(userObjectID);
    }
}
