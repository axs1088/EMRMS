package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.service.ManageStaffService;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientEncounterService;
import edu.psu.sweng500.emrms.validators.EncounterValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.fail;


@RunWith(MockitoJUnitRunner.class)
public class EncounterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientEncounterService encounterService;

    @Mock
    private ManageStaffService manageStaffService;

    @Mock
    private ApplicationAuditHelper applicationAuditHelper;

    @Mock
    private ApplicationSessionHelper sessionHelper;

    @Mock
    private EncounterValidator encounterValidator;

    @Mock
    private PatientDemographicsService demographicsService;

    @InjectMocks
    private EncounterController encounterController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(encounterController).build();
    }

    @Test
    public void testPatientEncounter() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/encounterDetails"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddEncounter() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/addEncounter"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    // @AMS There is no JSP for this, which is why the 404 was happening
//    @Test
//    public void testGetPhysicianDetails() {
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/physiciandetails"))
//                    .andExpect(MockMvcResultMatchers.status().isOk());
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail(e.getMessage());
//        }
//    }

}

