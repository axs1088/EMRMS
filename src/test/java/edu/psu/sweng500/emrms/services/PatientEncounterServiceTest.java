package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HBed;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.service.PatientEncounterService;

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

import static edu.psu.sweng500.emrms.util.Constants.ENCOUNTER_STATUS_ACTIVE;
import static edu.psu.sweng500.emrms.util.Constants.ENCOUNTER_STATUS_CLOSED;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class PatientEncounterServiceTest {
    @Autowired
    @Qualifier("saveEncounterService")
    private PatientEncounterService service;

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
        assertThat(service, instanceOf(PatientEncounterService.class));
    }

    @Test
    public void testSaveAndReviseEncounter() {
        HPatient hPatient = new HPatient();
        HEncounter hEncounter = new HEncounter();
        hEncounter.setUserID("admin");
        hEncounter.setEncStartDateTime("2017-09-14 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_ACTIVE);
        hEncounter.setEncLocationName("Exton Clinic");
        hEncounter.setEncounterType("OP");
        hEncounter.setEncounterID("Acc1001");
        hEncounter.setPatient_ObjectID(3);
        hEncounter.setEncounterReason("Headache");
        //hEncounter.setAttendingPhysician_ObjectID(1);
        //hEncounter.setBed_ObjectID(1);

        // ToDo
       int returnValue = service.AddEncounter(hPatient, hEncounter);
        assertEquals(0,returnValue);
       //Revise

        hEncounter.setHEncounterID(hEncounter.getHEncounterID());
        hEncounter.setEncStartDateTime("2018-09-14 11:55:00");
        hEncounter.setEncEndDateTime("2018-09-15 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_CLOSED);
        hEncounter.setEncLocationName("Malvern Clinic");
        hEncounter.setEncounterType("EOP");
        hEncounter.setEncounterID("Acc10012");
        hEncounter.setAttendingPhysician_ObjectID(2);
        hEncounter.setEncounterReason("TummyPain");

        returnValue = service.ReviseEncounter(hPatient, hEncounter);
        assertEquals(0,returnValue);

        hEncounter = new HEncounter();
        hEncounter.setUserID("admin");
        hEncounter.setEncStartDateTime("2017-09-14 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_ACTIVE);
        hEncounter.setEncLocationName("Exton Clinic");
        hEncounter.setEncounterType("IP");
        hEncounter.setEncounterID("Acc1001");
        hEncounter.setPatient_ObjectID(3);
        hEncounter.setAttendingPhysician_ObjectID(1);
        hEncounter.setBed_ObjectID(1);
        hEncounter.setEncounterReason("Belly Hurts");

        // ToDo
        returnValue = service.AddInPatientEncounter(hPatient, hEncounter);
        assertEquals(0,returnValue);

        hEncounter.setHEncounterID(hEncounter.getHEncounterID());
        hEncounter.setEncStartDateTime("2018-09-14 11:55:00");
        hEncounter.setEncEndDateTime("2018-09-15 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_CLOSED);
        hEncounter.setEncLocationName("Malvern Clinic");
        hEncounter.setEncounterType("IP");
        hEncounter.setEncounterID("Acc10012");
        hEncounter.setAttendingPhysician_ObjectID(2);
        hEncounter.setEncounterReason("Broken Legs");

        returnValue = service.ReviseInPatientEncounter(hPatient, hEncounter);
        assertEquals(0,returnValue);


    }

    @Test
    public void testGetPatientLocations() {
        List<HHealthcareOrganization>locations = service.GetPatientLocations("P");
        assertEquals(5,locations.size());
    }

    @Test
    public void testGetPatientLocationBeds() {
        List<HBed>beds = service.GetPatientLocationBeds("B",3);
        assertEquals(3,beds.size());

        beds.clear();
        beds = service.GetPatientLocationBeds("B",4);
        assertEquals(2,beds.size());

        beds.clear();
        beds = service.GetPatientLocationBeds("B",5);
        assertEquals(2,beds.size());

        beds.clear();
        beds = service.GetPatientLocationBeds("B",6);
        assertEquals(2,beds.size());
    }


}