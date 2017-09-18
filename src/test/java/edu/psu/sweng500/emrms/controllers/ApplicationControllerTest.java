package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.LocallyCachedCensusMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class ApplicationControllerTest {
    private ApplicationController applicationController;
    private CensusServiceImpl censusService;
    private LocallyCachedCensusMapper censusMapper;
    private HCensus validCensus;

    @Before
    public void setUp() {
        applicationController = new ApplicationController();
        censusService = new CensusServiceImpl();
        censusMapper = new LocallyCachedCensusMapper();

        validCensus = new HCensus();
        validCensus.setFirstName("Valid");
        validCensus.setLastName("User");

        censusMapper.addPhysicianCensus(validCensus);
        censusService.setCensusMapper(censusMapper);
        applicationController.setCensusService(censusService);
    }

    @Test
    public void testHandleRequestInternal() throws Exception {
        ModelAndView modelAndView = applicationController.handleRequestInternal(null, null);

        modelAndView.getModel().get("msg");

        String expectedMessage = ApplicationController.generateWelcomeMessage(validCensus);
        assertEquals(expectedMessage, modelAndView.getModel().get("msg"));
    }
}