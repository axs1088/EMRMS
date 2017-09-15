package edu.psu.sweng500.emrms.service;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.List;

public interface PhysicianCensusService {
    public List<HCensus> getPhysicianCensus(Integer userObjectID);
}
