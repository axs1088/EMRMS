package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.LocallyCachedUserMapper;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.UserServiceImpl;
import edu.psu.sweng500.emrms.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginControllerTest {
    private LoginController controller;
    private UserServiceImpl userService;
    private LocallyCachedUserMapper mapper;
    private User validUser;
    private User invalidUser;

    @Before
    public void setUp() {
        controller = new LoginController();
        userService = new UserServiceImpl();
        mapper = new LocallyCachedUserMapper();

        validUser = new User();
        validUser.setLoginId("Tester_McTesting");
        validUser.setUsername("Tester_McTesting");
        mapper.addUser(validUser);

        invalidUser = new User();
        invalidUser.setLoginId("invalid");
        invalidUser.setUsername("invalid");

        userService.setUserMapper(mapper);
        controller.setUserService(userService);
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
//        ModelAndView modelAndView = controller.loginProcess(null, null, validUser);
//        assertEquals(validUser.getLoginId(), modelAndView.getModel().get("firstname"));
    }

    @Test
    public void testLoginProcessWithInvalidUser() throws Exception {
        ModelAndView modelAndView = controller.loginProcess(null, null, invalidUser);
        assertEquals(Constants.INVALID_USER_MESSAGE, modelAndView.getModel().get("message"));
    }
}