package edu.psu.sweng500.emrms.services;


import edu.psu.sweng500.emrms.service.FindPatientService;

import edu.psu.sweng500.emrms.model.HCensus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/emrms-context.xml"})
public class FindPatientByDemographicsTest {
    @Autowired
    @Qualifier("findPatientByDemogrpahicsService")

    private FindPatientService findPatientService;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    public void verifySpringAnnotations() {
        assertThat(findPatientService, instanceOf(FindPatientService.class));
    }

    @Test
    public void testFindPatientWithAllParameters() {
        List<HCensus> patientList = findPatientService.getPatientListByDemogrpahics("D","J",1);
        // @AMS until we define how to use the database in our tests, leave this alone
        assertEquals(1, patientList.size());
    }

    @Test
    public void testFindPatientWithLastNameAndAllGender() {
        List<HCensus> patientList = findPatientService.getPatientListByDemogrpahics("D","",3);
        // @AMS until we define how to use the database in our tests, leave this alone
        assertEquals(2, patientList.size());
    }
}
