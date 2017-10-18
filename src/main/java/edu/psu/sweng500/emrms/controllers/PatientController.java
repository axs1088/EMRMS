package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.format.EMRMSCustomEditor;
import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.service.CensusService;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientService;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import edu.psu.sweng500.emrms.validators.PatientValidator;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static edu.psu.sweng500.emrms.util.Constants.SAVE_SUCESSFUL;

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
    private PatientDemographicsService patientDemographicsService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;
    
    @Autowired
    private PatientValidator patientValidator;

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     *
     * @param binder the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
        binder.registerCustomEditor(Integer.class, new EMRMSCustomEditor());
    }

    @RequestMapping(value = "/patientRegistration", method = RequestMethod.GET)
    public ModelAndView registerPatient(HttpServletRequest request, HttpServletResponse response) {
        applicationAuditHelper.auditEvent(request.getSession(false), "Patient Registation", 1);
        ModelAndView mav = new ModelAndView("patientRegistration");
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
        
        List<String> validationErrors = patientValidator.validate(patient);
        
        if(CollectionUtils.isNotEmpty(validationErrors)) {
        	mav.addObject("errorOnPage", true);
        	mav.addObject("validationErrors", validationErrors);
            return mav;
        }

        patientService.registerPatient(patient);
        mav.addObject("saveSuccess", true);

        int personId = patientDemographicsService.getPersonId(patient.getObjectID());
        HPatient patientFromDB = patientDemographicsService.getPatientDemographics(patient.getObjectID());
        HPerson personFromDB = patientDemographicsService.getPersonDetails(personId);
        HName nameFromDB = patientDemographicsService.getPersonName(personId);
        Address addressFromDB = patientDemographicsService.getPersonAddress(personId);
        List<HPatientId> patientIds = patientDemographicsService.getPatientIdentifiers(patient.getObjectID());
        List <HEncounter> encounters= patientDemographicsService.getPatientEncounters(patient.getObjectID());

        mav.addObject("person", patientFromDB);
        mav.addObject("patientname", nameFromDB);
        mav.addObject("patientaddress", addressFromDB);
        mav.addObject("patientids", patientIds);
        mav.addObject("encounters", encounters);

        return mav;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public void setCensusService(CensusService censusService) {
        this.censusService = censusService;
    }

    public void setPatientDemographicsService(PatientDemographicsService patientDemographicsService) {
        this.patientDemographicsService = patientDemographicsService;
    }

    public void setApplicationAuditHelper(ApplicationAuditHelper applicationAuditHelper) {
        this.applicationAuditHelper = applicationAuditHelper;
    }
}
