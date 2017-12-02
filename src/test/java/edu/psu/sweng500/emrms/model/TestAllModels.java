package edu.psu.sweng500.emrms.model;

import edu.psu.sweng500.emrms.testdrivers.GenericModelTester;
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
    public void testPhone() throws Exception {
        GenericModelTester.testAllGettersAndSetters(Phone.class);
    }

    @Test
    public void testUser() throws Exception {
        GenericModelTester.testAllGettersAndSetters(User.class);
    }

    @Test
    public void testEncounterAssessment() throws Exception {
        GenericModelTester.testAllGettersAndSetters(EncounterAssessment.class);
    }

    @Test
    public void testPatientDetails() throws Exception {
        GenericModelTester.testAllGettersAndSetters(PatientDetails.class);
    }

    @Test
    public void testHealthcareOrganization() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HHealthcareOrganization.class);
    }

    @Test
    public void testBed() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HBed.class);
    }

    @Test
    public void testPolicy() throws Exception {
        GenericModelTester.testAllGettersAndSetters(Policy.class);
    }

    @Test
    public void testStaff() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HStaff.class);
    }

    @Test
    public void testSiteHeader() throws Exception {
        GenericModelTester.testAllGettersAndSetters(SiteHeader.class);
    }

    @Test
    public void testDiagnozis() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HDiagnosis.class);
    }

    @Test
    public void testKnownAllergies() throws Exception {
        GenericModelTester.testAllGettersAndSetters(KnownAllergies.class);
    }

    @Test
    public void testActiveTabs() throws Exception {
        GenericModelTester.testAllGettersAndSetters(ActiveTabs.class);
    }

    @Test
    public void testName() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HName.class);
    }

    @Test
    public void testAssessment() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HAssessment.class);
    }

    @Test
    public void testProblem() throws Exception {
        GenericModelTester.testAllGettersAndSetters(HProblem.class);
    }
}
