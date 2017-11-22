package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.format.EMRMSCustomEditor;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HHealthcareOrganization;
import edu.psu.sweng500.emrms.model.HPatient;
import edu.psu.sweng500.emrms.model.HStaff;
import edu.psu.sweng500.emrms.service.ManageStaffService;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientEncounterService;
import edu.psu.sweng500.emrms.util.EMRMSProperties;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import edu.psu.sweng500.emrms.validators.EncounterValidator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class EncounterController {

    @Autowired
    private PatientEncounterService encounterService;
    
    @Autowired
    private ManageStaffService manageStaffService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;

    @Autowired
    private ApplicationSessionHelper sessionHelper;
    
    @Autowired
    private EncounterValidator encounterValidator;
    
    @Autowired
    private PatientDemographicsService demographicsService;
    
    List<String> attendingPhysicians = new ArrayList<String>();

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     *
     * @param binder the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
        //binder.registerCustomEditor(Integer.class, new EMRMSCustomEditor());
    }

    @RequestMapping(value = "/encounterDetails", method = RequestMethod.GET)
    public ModelAndView patientEncounter(HttpServletRequest request, @RequestParam(value = "hPatientID", required = false) Integer hPatientID) {
        HttpSession session = request.getSession(false);
        applicationAuditHelper.auditEvent(session, "View Encounter Details", 10, sessionHelper.getHPatientId(session), 0);
        ModelAndView mav = new ModelAndView("encounterTabShell");
        HEncounter hEncounter = new HEncounter();

        mav.addObject("showHeader", true);
        mav.addObject("encounter", hEncounter);
        sessionHelper.setActivePatient(sessionHelper.getHPatientId(session));
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav.addObject("encounterList", demographicsService.getPatientEncounters(sessionHelper.getHPatientId(session)));
        mav.addObject("patientLocations", getPatientLocations());
        
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }
    
    @RequestMapping(value = "/addEncounter", method = RequestMethod.POST)
    public ModelAndView addEncounter(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("encounter") HEncounter encounter, BindingResult bindingResult) throws Exception {
        ModelAndView mav = new ModelAndView("encounterTabShell");
        HttpSession session = request.getSession(false);
        List<String> validationErrors = encounterValidator.validate(encounter);
        
        if(StringUtils.isNotBlank(encounter.getAttendingPhysician())) {
        	if(!attendingPhysicians.contains(encounter.getAttendingPhysician())) {
	        	validationErrors.add(EMRMSProperties.get("patientencounter.validate.physiciannameoptions"));
	        } else {
	        	String physicianName = encounter.getAttendingPhysician();
	            Integer staffId = Integer.parseInt(physicianName.substring(physicianName.indexOf("(")+1, physicianName.indexOf(")")));
	            encounter.setAttendingPhysician_ObjectID(staffId);
	        }
        }

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            mav.addObject("errorOnPage", true);
            mav.addObject("validationErrors", validationErrors);
            mav.addObject("showHeader", true);
            mav.addObject("siteHeader", sessionHelper.getSiteHeader());
            return mav;
        }
        
        HPatient hPatient = new HPatient();
        
        encounter.setPatient_ObjectID(sessionHelper.getHPatientId(session));
        encounterService.AddEncounter(hPatient, encounter);
        
        mav.addObject("saveSuccess", true);
        mav.addObject("encounter", encounter);
        sessionHelper.setActivePatient(sessionHelper.getHPatientId(session));
        mav.addObject("showHeader", true);
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav.addObject("encounterList", demographicsService.getPatientEncounters(sessionHelper.getHPatientId(session)));
        mav.addObject("patientLocations", getPatientLocations());
        
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }
    
    @RequestMapping(value = "/physiciandetails", method = RequestMethod.GET)
    public @ResponseBody List<String> getPhysicianDetails(HttpServletRequest request, HttpServletResponse response,
    						@RequestParam(value = "searchString", required = false) String searchString) {
        List<HStaff> staffList = manageStaffService.GetStaffList(searchString);
        attendingPhysicians = new ArrayList<String>();
        
        if(CollectionUtils.isNotEmpty(staffList)) {
        	for(HStaff staff : staffList) {
        		String physicianName = staff.getStaffName();
        		physicianName = physicianName +" ("+ staff.getStaffId() +")";
        		attendingPhysicians.add(physicianName);
        	}
        }
        
        return attendingPhysicians;
    }
    
    private Map<Integer,String> getPatientLocations() {
        List<HHealthcareOrganization> patientLocations = encounterService.GetPatientLocations();
	    Map<Integer,String> locationsMap = new LinkedHashMap<Integer,String>();
	    if(CollectionUtils.isNotEmpty(patientLocations)) {
	        for (HHealthcareOrganization patientLocation : patientLocations) {
	        	locationsMap.put(patientLocation.getObjectId(), patientLocation.getName());
	        }
	    }
	    return locationsMap;
    }
    
}
