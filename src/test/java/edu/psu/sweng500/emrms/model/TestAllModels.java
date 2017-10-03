package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.testdrivers.GenericModelTester;
import org.junit.Ignore;
import org.junit.Test;

public class TestAllModels {
    @Test
    public void testAddress() throws Exception {
        GenericModelTester.testAllGettersAndSetters(Address.class);
    }

    @Test
    public void testComplexName() throws Exception {
        GenericModelTester.testAllGettersAndSetters(ComplexName.class);
    }

    @Test
    public void testHAuditRecord() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HAuditRecord.class);
    }

    @Test
    public void testHCensus() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HCensus.class);
    }

    @Test
    public void testHEncounter() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HEncounter.class);
    }

    @Test
    public void testHName() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HName.class);
    }

    @Test
    public void testHPatient() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HPatient.class);
    }

    @Test
    public void testHPatientId() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HPatientId.class);
    }

    @Test
    public void testHPerson() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HPerson.class);
    }

    @Test
    public void testPatientRegistrationModel() throws Exception {
        GenericModelTester.testAllGettersAndSetters(PatientRegistrationModel.class);
    }

    @Test
    public void testPhone() throws Exception {
        GenericModelTester.testAllGettersAndSetters(Phone.class);
    }

    @Test
    public void testUser() throws Exception {
        GenericModelTester.testAllGettersAndSetters(User.class);
    }
}
