package edu.psu.sweng500.emrms.validators;

import static org.junit.Assert.assertTrue;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import edu.psu.sweng500.emrms.model.Address;
import edu.psu.sweng500.emrms.model.ComplexName;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HPatientId;
import edu.psu.sweng500.emrms.model.Phone;

public class PatientValidatorTest {
	
	HPatient patient = null;
	PatientValidator patientValidator = null;
	
	@Before
    public void setUp() {  	
		patient = new HPatient();	
		patientValidator = new PatientValidator();
		patient.setUserId("JohnDoe");
		
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
	    patient.setHomePhone(phone);
	
	    HPatientId patientId = new HPatientId();
	    patientId.setIdValue("MRN-Test");
	    patientId.setIdType("MRN");
	    patientId.setIdIssuerName("MLH");
	    patientId.setIdIssuerId(1);
	    patient.setPatientIds(patientId);
	}

	@Test
    public void testValidate() throws Exception {		
		assertTrue(CollectionUtils.isEmpty(patientValidator.validate(patient)));
    }
	
}
