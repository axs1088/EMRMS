package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class GetPatientDemographicsTest {

    @Autowired
    @Qualifier("getPatientDemographics")
    private PatientDemographicsService demographicsService;

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
        assertThat(demographicsService, instanceOf(PatientDemographicsService.class));
    }

    @Test
    public void testPatientDemograhicsService() {
        int personId = demographicsService.getPersonId(3);
        assertEquals(3,personId);
        HPatient patientFromDB = demographicsService.getPatientDemographics(3);
        assertNotNull(patientFromDB);
        HPerson personFromDB =demographicsService.getPersonDetails(personId);
        assertNotNull(personFromDB);
        HName nameFromDB = demographicsService.getPersonName(personId);
        assertNotNull(nameFromDB);
        Address addressFromDB = demographicsService.getPersonAddress(personId);
        assertNotNull(addressFromDB);
        List<HPatientId> patientIds = demographicsService.getPatientIdentifiers(3);
        assertEquals(1,patientIds.size());
        List <HEncounter> encounters= demographicsService.getPatientEncounters(3);
        assertEquals(1,encounters.size());

    }

}
