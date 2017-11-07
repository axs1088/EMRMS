package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.format.EMRMSCustomEditor;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientService;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class EncounterController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDemographicsService patientDemographicsService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;


    @Autowired
    private ApplicationSessionHelper sessionHelper;

    private ModelAndView mav;
    private Integer patientId;

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

    @RequestMapping(value = "/encounterDetails", method = RequestMethod.GET)
    public ModelAndView patientEncounter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        applicationAuditHelper.auditEvent(session, "View Encounter Details", 10, sessionHelper.getHPatientId(session), 0);
        ModelAndView mav = new ModelAndView("encounterTabShell");
        HEncounter hEncounter = new HEncounter();

        mav.addObject("showHeader", true);
        mav.addObject("encounter", hEncounter);
        sessionHelper.setActivePatient(sessionHelper.getHPatientId(session));
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());

        mav = sessionHelper.addSessionHeplperAttributes(mav);

        return mav;
    }
}
