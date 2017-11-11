package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.ComplexName;
import edu.psu.sweng500.emrms.model.HStaff;
import edu.psu.sweng500.emrms.service.ManageStaffService;
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
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
public class ManageStaffServiceTest {
    @Autowired
    @Qualifier("manageStaffService")
    private ManageStaffService manageStaffService;

    @Before
    public void setUp() {
        verifySpringAnnotations();
    }

    @After
    public void tearDown() {
        //ToDO
    }

    public void verifySpringAnnotations() {
        assertThat(manageStaffService, instanceOf(ManageStaffService.class));
    }

    @Test
    public void testGetStaffList() {
        String searchString = "J";
        List<HStaff> staffList = manageStaffService.GetStaffList(searchString);
        String  staffName = staffList.get(1).getStaffName();
        assertEquals(5,staffList.size());

    }

}
