package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.PatientRegistrationModel;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.*;
import edu.psu.sweng500.emrms.util.Constants;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private AuditEventService auditEventService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCensusService(CensusServiceImpl censusService) {
        this.censusService = censusService;
    }

    public void setAuditEventService(AuditEventServiceImpl auditEventService) {
        this.auditEventService = auditEventService;
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
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        ModelAndView mav = null;
        List<HCensus> hCensusList = null;
        String census = null;
        HAuditRecord auditRecord = new HAuditRecord();
        HttpSession session = request.getSession(false);

        user.setLoginId(user.getUsername());
        User userFromDb = userService.validateUser(user.getLoginId(), user.getPassword());
        if (userFromDb != null) {
            session.setAttribute(Constants.APPLICATION_USER, user.getLoginId());

            mav = new ModelAndView("welcome");
            long userType = userFromDb.getUserType();

            if (Constants.USER_TYPE_ADMIN == userType) {
                hCensusList = censusService.getPhysicianCensus((int) userFromDb.getUserId());
                census = Constants.CENSUS_TYPE_PHYSICIAN;
            } else if (Constants.USER_TYPE_NURSE == userType) {
                hCensusList = censusService.getNurseCensus(1); //TODO: hard coded to 1 but will be replaced with variable
                census = Constants.CENSUS_TYPE_NURSE;
            } else {
                //TODO - need to put logic for patient
            }

            if (CollectionUtils.isNotEmpty(hCensusList)) {
                mav.addObject("hCensusList", hCensusList);
            }
            mav.addObject(Constants.CENSUS, census);

            applicationAuditHelper.auditEvent(session, "Login", 1);

        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", Constants.INVALID_USER_MESSAGE);
        }
        return mav;
    }

    @RequestMapping(value = "/patientLocator", method = RequestMethod.GET)
    public ModelAndView findPatient(HttpServletRequest request, HttpServletResponse response
            , @RequestParam("lName") String lName
            , @RequestParam("fName") String fName
            , @RequestParam("gender") Integer gender) {
        ModelAndView mav = null;

        mav = new ModelAndView("patientLocator");

        List<HCensus> hPatientList = censusService.getPatientListByDemographics(lName, fName, gender);

        if (CollectionUtils.isNotEmpty(hPatientList)) {
            mav.addObject("hPatientList", hPatientList);
        }


        return mav;
    }

    @RequestMapping(value = "/patientRegistration", method = RequestMethod.GET)
    public ModelAndView findPatient(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("patientRegistration");
        mav.addObject("patientRegistrationModel", new PatientRegistrationModel());
        return mav;
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ModelAndView addPatient(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("patientRegistrationModel") PatientRegistrationModel patientRegistrationModel) {
        ModelAndView mav = new ModelAndView("addPatient");
        return mav;
    }
}
