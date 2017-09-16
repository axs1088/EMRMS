package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.LocallyCachedPhysicianCensusMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.service.PhysicianCensusServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

public class ApplicationControllerTest {
    private ApplicationController applicationController;
    private PhysicianCensusServiceImpl censusService;
    private LocallyCachedPhysicianCensusMapper censusMapper;
    private HCensus validCensus;

    @Before
    public void setUp() {
        applicationController = new ApplicationController();
        censusService = new PhysicianCensusServiceImpl();
        censusMapper = new LocallyCachedPhysicianCensusMapper();

        validCensus = new HCensus();
        validCensus.setFirstName("Valid");
        validCensus.setLastName("User");

        censusMapper.addCensus(validCensus);
        censusService.setCensusMapper(censusMapper);
        applicationController.setPhysicianCensusService(censusService);
    }

    @Test
    public void testHandleRequestInternal() throws Exception {
        ModelAndView modelAndView = applicationController.handleRequestInternal(null, null);

        modelAndView.getModel().get("msg");

        String expectedMessage = ApplicationController.generateWelcomeMessage(validCensus);
        assertEquals(expectedMessage, modelAndView.getModel().get("msg"));
    }
}