package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HAssessment;
import edu.psu.sweng500.emrms.service.ManageAssessmentService;
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
public class ManageAssessmentServiceTest {
    @Autowired
    @Qualifier("manageAssessmentService")
    private ManageAssessmentService service;

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
        assertThat(service, instanceOf(ManageAssessmentService.class));
    }
    @Test
    public void testAddReviseAssessment() {
        HAssessment assessment = new HAssessment();
        assessment.setUserId("admin");
        assessment.setCollectedDateTime("2017-09-14 11:55:00");
        assessment.setAssessmentId(1);
        assessment.setHeight(72);
        assessment.setHeightmeasureId(1);
        assessment.setWeight(155);
        assessment.setWeightmeasureId(3);
        assessment.setDystolicBP(120);
        assessment.setSystolicBP(85);
        assessment.setTemperature(104);
        assessment.setTemperaturemeasureId(5);
        assessment.setPulse(82);
        assessment.setEncounterObjectId(1);
        assessment.setPatientObjectId(3);
        assessment.setStatus("COMPLETE");

        // ToDo
        int returnValue = service.AddAssessment(assessment);
        assertEquals(0,returnValue);

        assessment.setObjectId(assessment.getObjectId());
        assessment.setCollectedDateTime("2018-09-14 11:55:00");
        assessment.setAssessmentId(1);
        assessment.setHeight(71);
        assessment.setHeightmeasureId(2);
        assessment.setWeight(156);
        assessment.setWeightmeasureId(4);
        assessment.setDystolicBP(121);
        assessment.setSystolicBP(86);
        assessment.setTemperature(102);
        assessment.setTemperaturemeasureId(5);
        assessment.setPulse(81);
        assessment.setEncounterObjectId(1);
        assessment.setPatientObjectId(3);
        assessment.setStatus("COMPLETE");

        // ToDo
        returnValue = service.ReviseAssessment(assessment);
        assertEquals(0,returnValue);

    }
}
