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
    private User validAdminUser;
    private User invalidUser;
    private User validNurseUser;
    private CensusServiceImpl censusService;
    private LocallyCachedCensusMapper censusMapper;
    private HCensus physicianCensusReturned;
    private HCensus patientCensusReturned;
    private HCensus nurseCensusReturned;


    @Before
    public void setUp() {
        controller = new LoginController();
        userService = new UserServiceImpl();
        userMapper = new LocallyCachedUserMapper();

        validAdminUser = new User();
        validAdminUser.setLoginId("Tester_McTesting");
        validAdminUser.setUsername("Tester_McTesting");
        validAdminUser.setUserType(Constants.USER_TYPE_ADMIN);
        userMapper.addUser(validAdminUser);

        validNurseUser = new User();
        validNurseUser.setLoginId("TestNurse");
        validNurseUser.setUsername("Test_Nurse");
        validNurseUser.setUserType(Constants.USER_TYPE_NURSE);
        userMapper.addUser(validNurseUser);

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
        ModelAndView modelAndView = controller.loginProcess(null, null, invalidUser);
        assertEquals(Constants.INVALID_USER_MESSAGE, modelAndView.getModel().get("message"));
    }

    @Test
    public void testLoginProcessWithAdmin() throws Exception {
        ModelAndView modelAndView = controller.loginProcess(null, null, validAdminUser);
        List<HCensus> hCensusList = (List<HCensus>) modelAndView.getModel().get("hCensusList");
        assertFalse(hCensusList.isEmpty());
        assertEquals(physicianCensusReturned.getLastName(), hCensusList.get(0).getLastName());
    }

    @Test
    public void testLoginProcessWithNurse() throws Exception {
        ModelAndView modelAndView = controller.loginProcess(null, null, validNurseUser);
        List<HCensus> hCensusList = (List<HCensus>) modelAndView.getModel().get("hCensusList");
        assertFalse(hCensusList.isEmpty());
        assertEquals(nurseCensusReturned.getLastName(), hCensusList.get(0).getLastName());
    }


}