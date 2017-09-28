package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.service.SaveEncounterService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static edu.psu.sweng500.emrms.util.Constants.ENCOUNTER_STATUS_ACTIVE;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/emrms-context.xml"})
public class SaveEncounterServiceTest {
    @Autowired
    @Qualifier("saveEncounterService")
    private SaveEncounterService service;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
        //delete from h_encounter where HEncounterID = 1001;
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(SaveEncounterService.class));
    }

    @Test
    public void testSaveEncounter() {
        HPatient hPatient = new HPatient();
        HEncounter hEncounter = new HEncounter();
        hEncounter.setUserID("admin");
        hEncounter.setCreationDateTime("2017-09-23 21:55:00");
        hEncounter.setEncStartDateTime("2017-09-14 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_ACTIVE);
        hEncounter.setEncLocationName("Exton Clinic");
        hEncounter.setEncounterType("OP");
        hEncounter.setEncounterID("Acc1001");
        hEncounter.setPatient_ObjectID(3);
        hEncounter.setAttendingPhysician_ObjectID(1);
        hEncounter.setBed_ObjectID(1);


        // ToDo
       int returnValue = service.SaveEncounter(hPatient, hEncounter);
       assertEquals(0,returnValue);

    }


}