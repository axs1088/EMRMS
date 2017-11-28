package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.mappers.AuditMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedAuditMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedCensusMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedUserMapper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.AuditEventService;
import edu.psu.sweng500.emrms.service.AuditEventServiceImpl;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import edu.psu.sweng500.emrms.service.UserServiceImpl;
import edu.psu.sweng500.emrms.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static org.mockito.Mock.*;

import static org.junit.Assert.*;

public class LoginControllerTest {
    private LoginController controller;
    private UserServiceImpl userService;
    private LocallyCachedUserMapper userMapper;
    private User validAdminUser;
    private User invalidUser;
    private User validNurseUser;
    private CensusServiceImpl censusService;
    private LocallyCachedCensusMapper censusMapper;
    private LocallyCachedAuditMapper auditMapper;
    private HCensus physicianCensusReturned;
    private HCensus patientCensusReturned;
    private HCensus nurseCensusReturned;
    @Autowired
    private ApplicationSessionHelper applicationSessionHelper;

    @Autowired
    private ApplicationAuditHelper auditHelper;

    @Autowired
    private AuditEventServiceImpl auditEventService;


    @Before
    public void setUp() {
        controller = new LoginController();
        userService = new UserServiceImpl();
        userMapper = new LocallyCachedUserMapper();
        applicationSessionHelper = new ApplicationSessionHelper();

        applicationSessionHelper.setPhysicianName("Doctor");

        auditHelper = new ApplicationAuditHelper();
        auditHelper.setApplicationSessionHelper(applicationSessionHelper);
        auditMapper = new LocallyCachedAuditMapper();
        HAuditRecord auditRecord = new HAuditRecord();
        auditMapper.insertAuditRecord(auditRecord);
        auditEventService = new AuditEventServiceImpl();
        auditHelper.setAuditEventService(auditEventService);
        auditEventService.setAuditMapper(auditMapper);

        validAdminUser = new User();
        validAdminUser.setLoginId("admin");
        validAdminUser.setUsername("admin");
        validAdminUser.setPassword("emrms");
        validAdminUser.setUserType(Constants.USER_TYPE_ADMIN);
        userMapper.addUser(validAdminUser);

        validNurseUser = new User();
        validNurseUser.setLoginId("nurse01");
        validNurseUser.setUsername("nurse01");
        validNurseUser.setPassword("emrms");
        validNurseUser.setUserType(Constants.USER_TYPE_NURSE);
        userMapper.addUser(validNurseUser);

        invalidUser = new User();
        invalidUser.setLoginId("invalid");
        invalidUser.setUsername("invalid");
        invalidUser.setPassword("sss");

        userService.setUserMapper(userMapper);
        controller.setUserService(userService);
        controller.setApplicationSessionHelper(applicationSessionHelper);
        controller.setApplicationAuditHelper(auditHelper);

        censusService = new CensusServiceImpl();
        censusMapper = new LocallyCachedCensusMapper();

        physicianCensusReturned = new HCensus();
        physicianCensusReturned.setLastName("physician");
        censusMapper.addPhysicianCensus(physicianCensusReturned);

        nurseCensusReturned = new HCensus();
        nurseCensusReturned.setLastName("nurse");
        censusMapper.addNurseCensus(nurseCensusReturned);

        patientCensusReturned = new HCensus();
        patientCensusReturned.setLastName("patient");
        censusMapper.addPatientCensus(patientCensusReturned);

        censusService.setCensusMapper(censusMapper);
        controller.setCensusService(censusService);
    }

    @Test
    public void testShowHome() throws Exception {
        assertNotNull(controller.showHome(null, null));
    }

    @Test
    public void testShowLogin() throws Exception {
        ModelAndView modelAndView = controller.showLogin(null, null);
        assertNotNull(modelAndView);

        User user = (User) modelAndView.getModel().get("user");
        assertNotNull(user);
    }

    @Test
    public void testLoginProcessWithInvalidUser() throws Exception {
        HttpServletRequest  mockedRequest = new MockHttpServletRequest();
        ModelAndView modelAndView = controller.loginProcess(mockedRequest, null, invalidUser);
        assertEquals(Constants.INVALID_USER_MESSAGE, modelAndView.getModel().get("message"));
    }

    @Test
    public void testLoginProcessWithAdmin() throws Exception {
        HttpServletRequest  mockedRequest = new MockHttpServletRequest();
        ModelAndView modelAndView = controller.loginProcess(mockedRequest, null, validAdminUser);
        List<HCensus> hCensusList = (List<HCensus>) modelAndView.getModel().get("hCensusList");
        assertFalse(hCensusList.isEmpty());
        assertEquals(physicianCensusReturned.getLastName(), hCensusList.get(0).getLastName());
    }

    @Test
    public void testLoginProcessWithNurse() throws Exception {
        HttpServletRequest  mockedRequest = new MockHttpServletRequest();
        ModelAndView modelAndView = controller.loginProcess(mockedRequest, null, validNurseUser);
        List<HCensus> hCensusList = (List<HCensus>) modelAndView.getModel().get("hCensusList");
        assertFalse(hCensusList.isEmpty());
        assertEquals(nurseCensusReturned.getLastName(), hCensusList.get(0).getLastName());
    }


}