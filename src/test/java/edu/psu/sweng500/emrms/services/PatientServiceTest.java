package edu.psu.sweng500.emrms.services;

import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.service.PatientService;
import edu.psu.sweng500.emrms.util.RatifiedDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-emrms-context.xml"})
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
    
    @Test
    public void testInsertPerson() throws Exception {
    	
    	//UserId, CreationDateTime, OrganDonor, HPersonID " +
    	HPatient patient = new HPatient();
    	patient.setUserId("vkumar123");
    	patient.setCreationDateTime(getCurrentDateTime());
    	patient.setGender(1);
    	patient.setBirthDate("2017-10-16");
    	patient.setOrganDonor(true);
    	ComplexName name = new ComplexName();
    	name.setFirst("Terry");
    	name.setMiddle("M");
    	name.setLast("Barton");
    	patient.setName(name);
    	Address address = new Address();
    	address.setLine1("600 Test Ave");
    	address.setCity("Horsham");
    	address.setState("PA");
    	address.setZip("19044");
    	address.setCountry("United States");
    	patient.setAddress(address);
    	Phone phone = new Phone();
    	phone.setNumber("1234567890");
    	patient.setEmail("test@psu.edu");
    	patient.setCellPhone(phone);

        HPatientId patientId = new HPatientId();
        patientId.setIdValue("MRN-Test");
        patientId.setIdType("MRN");
        patientId.setIdIssuerName("MLH");
        patientId.setIdIssuerId(1);
        patient.setPatientIds(patientId);

        service.registerPatient(patient);
        patient.setObjectID(42);
        patient.setPersonId(44);
        patient.setGender(2);
        patient.setBirthDate("2018-10-16");
        patient.setRace("Hispanic");
        name.setFirst("ABC");
        name.setLast("DEF");
        name.setMiddle("IJK");
        name.setTitle("Mr.");
        name.setGenQualifier("Jr.");
        patient.setName(name);

        address.setLine1("ReviseStrAddr");
        address.setCity("Malvern");
        address.setState("MA");
        address.setZip("12345");
        address.setCountry("United States");
        patient.setAddress(address);

        phone.setNumber("098765432");
        patient.setCellPhone(phone);

        patient.setEmail("testrevise@psu.edu");

        Phone homePhone = new Phone();
        homePhone.setNumber("9833427665");
        patient.setHomePhone(homePhone);

        service.registerPatient(patient);

    }
    
	public String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
	}
}