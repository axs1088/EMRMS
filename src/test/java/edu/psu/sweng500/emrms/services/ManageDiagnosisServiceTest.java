package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HDiagnosis;
import edu.psu.sweng500.emrms.service.ManageDiagnosisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class ManageDiagnosisServiceTest {

    @Autowired
    @Qualifier("manageDiagnosisService")

    private ManageDiagnosisService service;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
        //delete from h_Diagnosis where hDiagnosisID = 1;
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(ManageDiagnosisService.class));
    }

    @Test
    public void testAddDiagnosis() {
        HDiagnosis diagnosis = new HDiagnosis();

        diagnosis.setUserId("admin");
        diagnosis.setCode("520.124");
        diagnosis.setDescription("Foot Fracture");
        diagnosis.setEncounterID(1);
        diagnosis.setPatientID(3);

        // ToDo
        int returnValue = service.AddDiagnosis(diagnosis);
        assertEquals(0,returnValue);

    }

    @Test
    public void testReviseDiagnosis() throws Exception {
        HDiagnosis diagnosis = new HDiagnosis();
        diagnosis.setDiagnosisObjectId(1);
        diagnosis.setUserId("admin");
        diagnosis.setCode("520.124567");
        diagnosis.setDescription("Foot Fracture Test Revised");
        diagnosis.setPriority(1);
        diagnosis.setEncounterID(1);
        diagnosis.setPatientID(3);

        // ToDo
        int returnValue = service.ReviseDiagnosis(diagnosis);
        assertEquals(0,returnValue);

    }

    @Test
    public void testDeleteDiagnosis() throws Exception {
        HDiagnosis diagnosis = new HDiagnosis();
        diagnosis.setDiagnosisObjectId(2);
        diagnosis.setPatientID(3);
        diagnosis.setEncounterID(1);

        // ToDo
        int returnValue = service.DeleteDiagnosis(diagnosis);
        assertEquals(0,returnValue);

    }
}
