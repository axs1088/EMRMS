package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.exceptions.PatientNotFoundException;
import edu.psu.sweng500.emrms.format.EMRMSCustomEditor;
import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.HAllergy;
import edu.psu.sweng500.emrms.model.HDiagnosis;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.KnownAllergies;
import edu.psu.sweng500.emrms.service.ManageAllergyService;
import edu.psu.sweng500.emrms.service.ManageDiagnosisService;
import edu.psu.sweng500.emrms.service.PatientDemographicsService;
import edu.psu.sweng500.emrms.service.PatientService;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartingController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDemographicsService patientDemographicsService;

    @Autowired
    private ApplicationAuditHelper applicationAuditHelper;

    @Autowired
    private ApplicationSessionHelper sessionHelper;

    @Autowired
    private ManageAllergyService manageAllergyService;

    @Autowired
    private ManageDiagnosisService manageDiagnosisService;

    @Autowired
    private PatientDemographicsMapper patientDemographicsMapper;

    private ModelAndView mav;
    private Integer patientId;
    private HttpSession session;

    private HAllergy newAllergy;
    private List<HAllergy> allergyList;

    private HDiagnosis newDiagnosis;
    private List<HDiagnosis> diagnosisList;


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

    @RequestMapping(value = "/charting", method = RequestMethod.GET)
    public ModelAndView showCharting(HttpServletRequest request, HttpServletResponse response) {
        sessionHelper.setActiveChartingTab("allergies");

        mav = new ModelAndView("chartingTabShell");
        session = request.getSession(false);

        mav.addObject("showHeader", true);
        sessionHelper.setActivePatient(sessionHelper.getHPatientId(session));
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());

        addAllergiesToMav();
        addDiagnosesToMav();

        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    private void addAllergiesToMav() {
        newAllergy = new HAllergy();

        try {
            patientId = sessionHelper.getPatientId();
            allergyList = patientDemographicsService.getPatientAllergies(patientId);
            newAllergy.setPatientID(patientId);
        } catch (PatientNotFoundException e) {
            allergyList = new ArrayList<>();
        }

        newAllergy.setUserId(sessionHelper.getApplicationUser(session));

        mav.addObject("allergyList", allergyList);
        mav.addObject("newAllergy", newAllergy);
        mav.addObject("deletedAllergy", new HAllergy());

        KnownAllergies knownAllergies = new KnownAllergies();
        knownAllergies.setNoKnownAllergies(allergyList.isEmpty());
        knownAllergies.setNoKnownFoodAllergies(allergyList.stream().noneMatch(allergy -> allergy.getAllergyType() == 1));
        knownAllergies.setNoKnownDrugAllergies(allergyList.stream().noneMatch(allergy -> allergy.getAllergyType() == 2));
        mav.addObject("knownAllergies", knownAllergies);
    }

    @RequestMapping(value = "/addAllergy", method = RequestMethod.POST)
    public ModelAndView addAllergy(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("newAllergy") HAllergy allergy, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("allergies");

        newAllergy.setAllergyName(allergy.getAllergyName());
        newAllergy.setSeverity(allergy.getSeverity());
        newAllergy.setAllergyType(allergy.getAllergyType());
        manageAllergyService.AddAllergy(newAllergy);

        addAllergiesToMav();
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    @RequestMapping(value = "/deleteAllergy", method = RequestMethod.POST)
    public ModelAndView deleteAllergy(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("deletedAllergy") HAllergy deletedAllergy, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("allergies");

        try {
            final int deletedAllergyId = deletedAllergy.getAllergyID();
            deletedAllergy = allergyList.stream()
                    .filter(allergy -> allergy.getAllergyID() == deletedAllergyId)
                    .findFirst()
                    .get();

            manageAllergyService.DeleteAllergy(deletedAllergy);
        } catch (Exception e) {
            // Fine
        }

        addAllergiesToMav();
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    private void addDiagnosesToMav() {
        newDiagnosis = new HDiagnosis();

        try {
            patientId = sessionHelper.getPatientId();
            diagnosisList = patientDemographicsService.getPatientDiagnoses(patientId);
            newDiagnosis.setPatientID(patientId);
        } catch (PatientNotFoundException e) {
            diagnosisList = new ArrayList<>();
        }

        newDiagnosis.setUserId(sessionHelper.getApplicationUser(session));

        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientId);

        if (encounters != null && !encounters.isEmpty()) {
            newDiagnosis.setEncounterID(encounters.get(0).getHEncounterID());
        }

        mav.addObject("diagnosisList", diagnosisList);
        mav.addObject("newDiagnosis", newDiagnosis);
        mav.addObject("deletedDiagnosis", new HDiagnosis());
    }

    @RequestMapping(value = "/addDiagnosis", method = RequestMethod.POST)
    public ModelAndView addAllergy(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("newDiagnosis") HDiagnosis diagnosis, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("diagnoses");

        newDiagnosis.setDescription(diagnosis.getDescription());
        newDiagnosis.setCode(diagnosis.getCode());
        newDiagnosis.setPriority(diagnosis.getPriority());

        manageDiagnosisService.AddDiagnosis(newDiagnosis);

        addDiagnosesToMav();
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }

    @RequestMapping(value = "/deleteDiagnosis", method = RequestMethod.POST)
    public ModelAndView deleteDiagnosis(HttpServletRequest request, HttpServletResponse response,
                                        @ModelAttribute("deletedDiagnosis") HDiagnosis deletedDiagnosis, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("diagnoses");

        try {
            final int deletedDiagnosisId = deletedDiagnosis.getDiagnosisObjectId();
            deletedDiagnosis = diagnosisList.stream()
                    .filter(diagnosis -> diagnosis.getDiagnosisObjectId() == deletedDiagnosisId)
                    .findFirst()
                    .get();

            manageDiagnosisService.DeleteDiagnosis(deletedDiagnosis);
        } catch (Exception e) {
            // Fine
        }

        addDiagnosesToMav();
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());
        mav = sessionHelper.addSessionHelperAttributes(mav);

        return mav;
    }
}
