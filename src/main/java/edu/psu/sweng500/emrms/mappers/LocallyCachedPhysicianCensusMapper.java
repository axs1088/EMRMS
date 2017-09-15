package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedPhysicianCensusMapper implements PhysicianCensusMapper {
    private List<HCensus> hCensusList;

    public LocallyCachedPhysicianCensusMapper() {
        hCensusList = new ArrayList<>();
    }

    @Override
    public List<HCensus> getPhysicianCensus(Integer userObjectID) {
        return hCensusList;
    }

    public void addCensus(HCensus census) {
        hCensusList.add(census);
    }
}
