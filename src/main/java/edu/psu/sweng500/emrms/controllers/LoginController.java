package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.model.HAuditRecord;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.User;
import edu.psu.sweng500.emrms.service.*;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void setCensusService(CensusServiceImpl censusService) {
        this.censusService = censusService;
    }
    public void setAuditEventService(AuditEventServiceImpl auditEventService){
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


        user.setLoginId(user.getUsername());
        User userFromDb = userService.validateUser(user.getLoginId(), user.getPassword());
        if (userFromDb != null) {
            mav = new ModelAndView("welcome");
            long userType = userFromDb.getUserType();
            
            if(Constants.USER_TYPE_ADMIN == userType) {
            	hCensusList = censusService.getPhysicianCensus((int) userFromDb.getUserId());
            	census = Constants.CENSUS_TYPE_PHYSICIAN;
            } else if(Constants.USER_TYPE_NURSE == userType) {
            	hCensusList = censusService.getNurseCensus(1); //TODO: hard coded to 1 but will be replaced with variable
            	census = Constants.CENSUS_TYPE_NURSE;
            } else {
            	//TODO - need to put logic for patient
            }

            if (CollectionUtils.isNotEmpty(hCensusList)) {
                mav.addObject("hCensusList", hCensusList);
            }
            mav.addObject(Constants.CENSUS, census);

            //Audit Login Event
            auditRecord.setEventName("Login");
            auditRecord.setPolicyId(1);
            auditRecord.setUserId(user.getLoginId());

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            auditRecord.setCreationDateTime(dateFormat.format(date));

            int returnValue = auditEventService.auditEvent(auditRecord);

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


}
