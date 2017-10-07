package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.PatientRegistrationModel;
import edu.psu.sweng500.emrms.service.*;
import edu.psu.sweng500.emrms.util.Constants;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * @author vkumar
 * The Class PatientController.
 */
@Controller
public class PatientController {
	
    @Autowired
    private PatientService patientService;

    @Autowired
    private CensusService censusService;
    
    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     *
     * @param binder the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
    }
    
    @RequestMapping(value = "/patientRegistration", method = RequestMethod.GET)
    public ModelAndView registerPatient(HttpServletRequest request, HttpServletResponse response) {
    	applicationAuditHelper.auditEvent(request.getSession(false), "Patient Registation", 1);
    	ModelAndView mav = new ModelAndView("patientRegistration");
        mav.addObject("patientRegistrationModel", new PatientRegistrationModel());
        HPatient patient = new HPatient();
        mav.addObject("patient", patient);
        
        return mav;
    }
    
    @RequestMapping(value = "/patientLocator", method = RequestMethod.GET)
    public ModelAndView showPatientLocator(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("patientLocator");
        mav.addObject("census", new HCensus());
        return mav;
    }

    @RequestMapping(value = "/patientLocatorProcess", method = RequestMethod.POST)
    public ModelAndView findPatient(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("census") HCensus patient) {
        ModelAndView mav = null;
        HttpSession session = request.getSession(false);

        mav = new ModelAndView("patientLocator");

        List<HCensus> hPatientList = censusService.getPatientListByDemographics(patient.getLastName(), patient.getFirstName(), patient.getGender());

        if (CollectionUtils.isNotEmpty(hPatientList)) {
            mav.addObject("hPatientList", hPatientList);
        }
        applicationAuditHelper.auditEvent(session, "Patient Locator", 4);
        return mav;
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ModelAndView addPatient(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("patient") HPatient patient, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("patientRegistration");
        
        patientService.registerPatient(patient);

        mav.addObject("message", Constants.SAVE_SUCESSFUL);
        
        return mav;
    }

}
