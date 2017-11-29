package edu.psu.sweng500.emrms.controllers;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MockMvc;

import edu.psu.sweng500.emrms.service.ManageAllergyService;
import edu.psu.sweng500.emrms.service.ManageAssessmentService;
import edu.psu.sweng500.emrms.service.ManageDiagnosisService;
import edu.psu.sweng500.emrms.service.ManageProblemService;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientService;
import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HAssessment;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HProblem;

 
@RunWith(MockitoJUnitRunner.class)
public class ChartingControllerTest {
 
    private MockMvc mockMvc;
	
    @Mock
    private PatientService patientService;

    @Mock
    private PatientDemographicsService patientDemographicsService;

    @Mock
    private ApplicationAuditHelper applicationAuditHelper;

    @Mock
    private ApplicationSessionHelper sessionHelper;

    @Mock
    private ManageAllergyService manageAllergyService;

    @Mock
    private ManageDiagnosisService manageDiagnosisService;

    @Mock
    private ManageAssessmentService manageAssessmentService;

    @Mock
    private ManageProblemService manageProblemService;

    @Mock
    private PatientDemographicsMapper patientDemographicsMapper;

    @Mock
    private ChartingMapper chartingMapper;

    @Mock
    private HAllergy newAllergy;

    @Mock
    private HDiagnosis newDiagnosis;

    @Mock
    private HAssessment newAssessment;

    @Mock
    private HProblem newProblem;
    
    @Mock
    private HEncounter currentEncounter;
    
	@InjectMocks
	private ChartingController chartingController;
 
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chartingController).build();
    }
    
    @Test
    public void testShowCharting() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/charting"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddAllergy() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/addAllergy"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteAllergy() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/deleteAllergy"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAddDiagnosis() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/addDiagnosis"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteDiagnosis() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/deleteDiagnosis"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAddAssessment() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/addAssessment"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteAssessment() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/deleteAssessment"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAddProblem() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/addProblem"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteProblem() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/deleteProblem"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
}

