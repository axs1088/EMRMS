package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedAuditMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedCensusMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedPatientDemographicMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedPatientMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.service.AuditEventServiceImpl;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import edu.psu.sweng500.emrms.service.PatientDemographicsServiceImpl;
import edu.psu.sweng500.emrms.service.PatientServiceImpl;
import edu.psu.sweng500.emrms.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientControllerTest {

    private PatientController controller;
    private CensusServiceImpl censusService;
    private PatientDemographicsServiceImpl patientDemographicsService;
    private ApplicationAuditHelper applicationAuditHelper;
    private LocallyCachedCensusMapper censusMapper;
    private LocallyCachedAuditMapper auditMapper;
    private LocallyCachedPatientMapper patientMapper;
    private LocallyCachedPatientDemographicMapper patientDemographicMapper;
    private HCensus patientCensusReturned;
    private PatientServiceImpl patientService;
    private ApplicationSessionHelper applicationSessionHelper;
    private AuditEventServiceImpl auditEventService;
    private HttpServletRequest mockedRequest;
    private HPatient patientReturned;

    @Before
    public void setUp() {
        auditMapper = new LocallyCachedAuditMapper();
        applicationSessionHelper = new ApplicationSessionHelper();
        auditEventService = new AuditEventServiceImpl();
        auditEventService.setAuditMapper(auditMapper);
        applicationAuditHelper = new ApplicationAuditHelper();
        applicationAuditHelper.setAuditEventService(auditEventService);
        applicationAuditHelper.setApplicationSessionHelper(applicationSessionHelper);

        patientReturned = new HPatient();
        patientReturned.setUserId("JohnDoe");
        patientMapper = new LocallyCachedPatientMapper();
        patientMapper.insertPatient(patientReturned);
        patientService = new PatientServiceImpl();
        patientService.setPatientMapper(patientMapper);
        censusService = new CensusServiceImpl();
        patientDemographicMapper = new LocallyCachedPatientDemographicMapper();
        patientDemographicsService = new PatientDemographicsServiceImpl();
        patientDemographicsService.setPatientDemographicsMapper(patientDemographicMapper);

        controller = new PatientController();
        controller.setCensusService(censusService);
        controller.setApplicationAuditHelper(applicationAuditHelper);
        controller.setPatientDemographicsService(patientDemographicsService);
        controller.setPatientService(patientService);

        censusService = new CensusServiceImpl();
        censusMapper = new LocallyCachedCensusMapper();

        patientCensusReturned = new HCensus();
        patientCensusReturned.setLastName("Doe");
        patientCensusReturned.setFirstName("John");
        censusMapper.addPatientCensus(patientCensusReturned);
        censusService.setCensusMapper(censusMapper);
        controller.setCensusService(censusService);

        String userName = "username";
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute(Constants.APP_USER_ID)).thenReturn(userName);
        mockedRequest = mock(HttpServletRequest.class);
        when(mockedRequest.getSession(false)).thenReturn(session);

    }

    @Test
    public void testShowPatientLocator() {
        ModelAndView modelAndView = controller.showPatientLocator(null, null);
        assertNotNull(modelAndView);

        HCensus census = (HCensus) modelAndView.getModel().get("census");
        assertNotNull(census);
    }

    @Test
    public void testFindPatientWithAllParameters() {
        List<HCensus> patientList = censusService.getPatientListByDemographics("D", "J", 1);
        assertEquals(1, patientList.size());
    }

    @Test
    public void testFindPatientWithLastNameAndAllGender() {
        List<HCensus> patientList = censusService.getPatientListByDemographics("D", "", 3);
        assertEquals(1, patientList.size());
    }

    @Test
    public void testRegisterPatient() {
        assertNotNull(controller.registerPatient(mockedRequest, null));
    }

    @Test
    public void testFindPatient() {
        assertNotNull(controller.findPatient(mockedRequest, null, patientCensusReturned));
    }

    @Test
    public void testAddPatient() {
        assertNotNull(controller.addPatient(mockedRequest, null, patientReturned, null));
    }
}
