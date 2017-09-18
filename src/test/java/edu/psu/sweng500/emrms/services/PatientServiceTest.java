package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.service.PatientService;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/emrms-context.xml"})
public class PatientServiceTest {
    @Autowired
    @Qualifier("patientService")
    private PatientService service;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(PatientService.class));
    }

    @Test
    public void testCreate() {
        HPatient testPatient = service.createNew();
        assertNull(testPatient);
    }

    @Test
    public void testReadAll() {
        List<HPatient> patientList = service.readAll();
        // @AMS until we define how to use the database in our tests, leave this alone
//        assertEquals(0, patientList.size());
    }
}