package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import edu.psu.sweng500.emrms.service.ManageAllergyService;
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
public class ManageAllergyServiceTest {
    @Autowired
    @Qualifier("manageAllergyService")

    private ManageAllergyService service;

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
        assertThat(service, instanceOf(ManageAllergyService.class));
    }

    @Test
    public void testAddAllergy() {
        HAllergy allergy = new HAllergy();
        allergy.setAllergyID(1);
        allergy.setUserId("admin");
        allergy.setAllergyCode("ALGCODE");
        allergy.setAllergyName("NUT Allergy");
        allergy.setAllergyType(1);
        allergy.setSeverity("High");
        allergy.setPatientID(3);

        // ToDo
        int returnValue = service.AddAllergy(allergy);
        assertEquals(0,returnValue);

    }

    @Test
    public void testDeleteAllergy()throws Exception{
        HAllergy allergy=new HAllergy();
        allergy.setAllergyID(1);
        allergy.setPatientID(3);

        //ToDo
        int returnValue=service.DeleteAllergy(allergy);
        assertEquals(0,returnValue);

    }




}
