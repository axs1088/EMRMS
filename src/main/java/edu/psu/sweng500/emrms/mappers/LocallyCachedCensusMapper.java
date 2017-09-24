package edu.psu.sweng500.emrms.mappers;

import edu.psu.sweng500.emrms.model.HCensus;

import java.util.ArrayList;
import java.util.List;

public class LocallyCachedCensusMapper implements CensusMapper {
    private List<HCensus> hPhysicianCensusList;
    private List<HCensus> hNurseCensusList;
    private List<HCensus> hPatientCensusList;

    public LocallyCachedCensusMapper() {
        hPhysicianCensusList = new ArrayList<>();
        hNurseCensusList = new ArrayList<>();
        hPatientCensusList = new ArrayList<>();
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

    @Override
    public List<HCensus> getPatientListByDemographics(String lName, String fName, Integer gender) {
        return hPatientCensusList;
    }

    public void addNurseCensus(HCensus census) {
        hNurseCensusList.add(census);
    }

    public void addPatientCensus(HCensus patientCensus) {
        hPatientCensusList.add(patientCensus);
    }
}
