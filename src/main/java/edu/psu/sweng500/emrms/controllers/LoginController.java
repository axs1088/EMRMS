package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.mappers.FindPatientMapper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HPerson;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.CensusService;
import edu.psu.sweng500.emrms.service.FindPatientService;
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
    private FindPatientService findPatientService;

    public void setUserService(UserService userService) {
        this.userService = userService;
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
        user.setLoginId(user.getUsername());
        User userFromDb = userService.validateUser(user.getLoginId(), user.getPassword());
        if (userFromDb != null) {
            mav = new ModelAndView("welcome");

            List<HCensus> hCensusList = censusService.getPhysicianCensus((int) userFromDb.getUserId());

            if (CollectionUtils.isNotEmpty(hCensusList)) {
                mav.addObject("hCensusList", hCensusList);
            }

        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", Constants.INVALID_USER_MESSAGE);
        }
        return mav;
    }

    @RequestMapping(value = "/patientLocator", method = RequestMethod.GET)
    public ModelAndView findPatient(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("lName") String lName,@ModelAttribute("fName") String fName, @ModelAttribute("gender") Integer gender) {
        ModelAndView mav = null;

        mav = new ModelAndView("patientLocator");

        List<HCensus> hPatientList = findPatientService.getPatientListByDemogrpahics(lName, fName, gender);

        if (CollectionUtils.isNotEmpty(hPatientList)) {
            mav.addObject("hPatientList", hPatientList);
        }


        return mav;
    }

}
