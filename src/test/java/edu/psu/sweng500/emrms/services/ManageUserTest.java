package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.PatientEncounterService;
import edu.psu.sweng500.emrms.service.UserService;
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
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class ManageUserTest {
    @Autowired
    @Qualifier("userService")
    private UserService service;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
    }

    public void verifySpringAnnotations() {
        assertThat(service, instanceOf(UserService.class));
    }

    @Test
    public void testLoggedinEntityDetails() {
        List<HHealthcareOrganization> locations = service.getLoggedinEntityDetails();
        assertEquals(1,locations.size());
        assertEquals("Paoli Hospital", locations.get(0).getName());
    }

    @Test
    public void testLoggedinUserDetails() {
        User user = service.getLoggedinUserDetails(1);
        assertEquals("Jeff, Jane T RN", user.getUsername());
    }
}
