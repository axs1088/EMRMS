package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.LocallyCachedCensusMapper;
import edu.psu.sweng500.emrms.mappers.LocallyCachedUserMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import edu.psu.sweng500.emrms.service.UserServiceImpl;
import edu.psu.sweng500.emrms.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;

public class LoginControllerTest {
    private LoginController controller;
    private UserServiceImpl userService;
    private LocallyCachedUserMapper userMapper;
    private User validUser;
    private User invalidUser;
    private CensusServiceImpl censusService;
    private LocallyCachedCensusMapper censusMapper;
    private HCensus physicianCensusReturned;
    private HCensus patientCensusReturned;

    @Before
    public void setUp() {
        controller = new LoginController();
        userService = new UserServiceImpl();
        userMapper = new LocallyCachedUserMapper();

        validUser = new User();
        validUser.setLoginId("Tester_McTesting");
        validUser.setUsername("Tester_McTesting");
        userMapper.addUser(validUser);

        invalidUser = new User();
        invalidUser.setLoginId("invalid");
        invalidUser.setUsername("invalid");

        userService.setUserMapper(userMapper);
        controller.setUserService(userService);

        censusService = new CensusServiceImpl();
        censusMapper = new LocallyCachedCensusMapper();

        physicianCensusReturned = new HCensus();
        physicianCensusReturned.setLastName("physician");
        censusMapper.addPhysicianCensus(physicianCensusReturned);

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
    public void testLoginProcessWithValidUser() throws Exception {
        ModelAndView modelAndView = controller.loginProcess(null, null, validUser);
        List<HCensus> hCensusList = (List<HCensus>) modelAndView.getModel().get("hCensusList");
        assertFalse(hCensusList.isEmpty());
        assertEquals(physicianCensusReturned.getLastName(), hCensusList.get(0).getLastName());
    }

    @Test
    public void testLoginProcessWithInvalidUser() throws Exception {
        ModelAndView modelAndView = controller.loginProcess(null, null, invalidUser);
        assertEquals(Constants.INVALID_USER_MESSAGE, modelAndView.getModel().get("message"));
    }

    @Test
    public void testFindPatient() {
        ModelAndView modelAndView = controller.findPatient(null, null, "last", "first", 3);

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
}