package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/emrms-context.xml"})
public class PatientServiceTest {
    @Autowired
    @Qualifier("patientService")
    private PatientService thisService;

    @Test
    public void testSpringAnnotations() {
        //assert correct type/impl
        assertThat(thisService, instanceOf(PatientService.class));
    }

    @Test
    public void alwaysPasses() {
    }
}