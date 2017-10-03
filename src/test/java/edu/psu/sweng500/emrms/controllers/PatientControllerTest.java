package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.CensusMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedCensusMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatientControllerTest {

    private PatientController controller;
    private CensusServiceImpl censusService;
    private LocallyCachedCensusMapper censusMapper;
    private HCensus patientCensusReturned;


    @Before
    public void setUp() {
        controller = new PatientController();
        censusService = new CensusServiceImpl();
        censusMapper = new LocallyCachedCensusMapper();

        patientCensusReturned = new HCensus();
        patientCensusReturned.setLastName("Doe");
        patientCensusReturned.setFirstName("John");
        censusMapper.addPatientCensus(patientCensusReturned);
        censusService.setCensusMapper(censusMapper);
    }

    @Test
    public void testShowPatientLocator() {
        ModelAndView modelAndView = controller.showPatientLocator(null,null);
        assertNotNull(modelAndView);

        HCensus census = (HCensus) modelAndView.getModel().get("census");
        assertNotNull(census);
    }

    @Test
    public void testFindPatientWithAllParameters() {
        List<HCensus> patientList = censusService.getPatientListByDemographics("D", "J", 1);
        assertEquals(1, patientList.size());
    }

    @Test
    public void testFindPatientWithLastNameAndAllGender() {
        List<HCensus> patientList = censusService.getPatientListByDemographics("D", "", 3);
        assertEquals(1, patientList.size());
    }
}
