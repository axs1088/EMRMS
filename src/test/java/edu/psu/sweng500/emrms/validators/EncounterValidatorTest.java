package edu.psu.sweng500.emrms.validators;

import static edu.psu.sweng500.emrms.util.Constants.ENCOUNTER_STATUS_ACTIVE;
import static org.junit.Assert.assertTrue;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import edu.psu.sweng500.emrms.model.HEncounter;

public class EncounterValidatorTest {
	
	HEncounter encounter = null;
	EncounterValidator encounterValidator = null;
	
	@Before
    public void setUp() {  	
		encounter = new HEncounter();	
		encounterValidator = new EncounterValidator();
        HEncounter hEncounter = new HEncounter();
        hEncounter.setUserID("admin");
        hEncounter.setEncStartDateTime("2017-09-14 11:55:00");
        hEncounter.setEncStatus(ENCOUNTER_STATUS_ACTIVE);
        hEncounter.setEncLocationName("Exton Clinic");
        hEncounter.setEncounterType("OP");
        hEncounter.setEncounterID("Acc1001");
        hEncounter.setPatient_ObjectID(3);
        hEncounter.setEncounterReason("Headache");
	}

	@Test
    public void testValidate() throws Exception {		
		assertTrue(CollectionUtils.isEmpty(encounterValidator.validate(encounter)));
    }

	
}
