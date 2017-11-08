package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.AuditEventServiceImpl;
import edu.psu.sweng500.emrms.service.CensusService;
import edu.psu.sweng500.emrms.service.CensusServiceImpl;
import edu.psu.sweng500.emrms.service.UserService;
import edu.psu.sweng500.emrms.util.Constants;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vkumar
 * The Class ApplicationController.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CensusService censusService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;

    @Autowired
    private ApplicationSessionHelper sessionHelper;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCensusService(CensusServiceImpl censusService) {
        this.censusService = censusService;
    }

    public void setAuditEventService(AuditEventServiceImpl auditEventService) {
    }

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     *
     * @param binder the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("home");

        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());

        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        HttpSession session = request.getSession(false);
        if (session != null)
            sessionHelper.clearAllSessionAttributes(session);
        //session.invalidate();

        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("login");
        List<HCensus> hCensusList = null;
        String census = null;
        HttpSession session = request.getSession();
        user.setLoginId(user.getUsername());

        Integer userId = null;
        Long userType = null;

        if (sessionHelper.getApplicationUserId(session) != null ||
                sessionHelper.getApplicationUserType(session) != null) {
            mav = new ModelAndView("welcome");
            userId = sessionHelper.getApplicationUserId(session);
            userType = sessionHelper.getApplicationUserType(session);
        } else {
            User userFromDb = userService.validateUser(user.getLoginId(), user.getPassword());
            if (userFromDb != null) {
                session.setAttribute(Constants.APP_USER_LOGIN_ID, user.getLoginId());
                mav = new ModelAndView("welcome");
                userType = userFromDb.getUserType();
                userId = (int) userFromDb.getUserId();
                session.setAttribute(Constants.APP_USER_ID, userId);
                session.setAttribute(Constants.APP_USER_TYPE, userType);
            } else {
                mav.addObject("message", Constants.INVALID_USER_MESSAGE);
            }
        }

        if (userId != null && userType != null) {
            if (Constants.USER_TYPE_ADMIN == userType) {
                hCensusList = censusService.getPhysicianCensus(userId);
                census = Constants.CENSUS_TYPE_PHYSICIAN;
            } else if (Constants.USER_TYPE_NURSE == userType) {
                hCensusList = censusService.getNurseCensus(1); //TODO: hard coded to 1 but will be replaced with variable
                census = Constants.CENSUS_TYPE_NURSE;
            } else {
                //TODO - need to put logic for patient
            }

            if (CollectionUtils.isEmpty(hCensusList)) {
                hCensusList = new ArrayList<HCensus>();
            }

            mav.addObject("showHeader", false);
            mav.addObject("hCensusList", hCensusList);
            mav.addObject(Constants.CENSUS, census);

            applicationAuditHelper.auditEvent(session, "Login", 1, 0, 0);
            applicationAuditHelper.auditEvent(session, "View Patient Census", 3, 0, 0);
        }

        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

}
