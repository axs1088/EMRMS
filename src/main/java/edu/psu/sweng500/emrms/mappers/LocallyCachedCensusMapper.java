package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedCensusMapper implements CensusMapper {
    private List<HCensus> hPhysicianCensusList;
    private List<HCensus> hNurseCensusList;

    public LocallyCachedCensusMapper() {
    	hPhysicianCensusList = new ArrayList<>();
    	hNurseCensusList = new ArrayList<>();
    }

    @Override
    public List<HCensus> getPhysicianCensus(Integer userObjectID) {
        return hPhysicianCensusList;
    }

    public void addPhysicianCensus(HCensus census) {
    	hPhysicianCensusList.add(census);
    }

	@Override
	public List<HCensus> getNurseCensus(Integer locationObjectID) {
		return hNurseCensusList;
	}
    
	public void addNurseCensus(HCensus census) {
		hNurseCensusList.add(census);
    }
    
}
