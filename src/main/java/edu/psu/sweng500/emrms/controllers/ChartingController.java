package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.application.ApplicationAuditHelper;
import edu.psu.sweng500.emrms.application.ApplicationSessionHelper;
import edu.psu.sweng500.emrms.exceptions.PatientNotFoundException;
import edu.psu.sweng500.emrms.format.EMRMSCustomEditor;
import edu.psu.sweng500.emrms.mappers.ChartingMapper;
import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.*;
import edu.psu.sweng500.emrms.service.*;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private ManageAssessmentService manageAssessmentService;

    @Autowired
    private ManageProblemService manageProblemService;

    @Autowired
    private PatientDemographicsMapper patientDemographicsMapper;

    @Autowired
    private ChartingMapper chartingMapper;

    private ModelAndView mav;
    private Integer patientId;
    private HEncounter currentEncounter;
    private HttpSession session;

    private HAllergy newAllergy;
    private List<HAllergy> allergyList;

    private HDiagnosis newDiagnosis;
    private List<HDiagnosis> diagnosisList;

    private HAssessment newAssessment;
    private List<HAssessment> assessmentList;

    private HProblem newProblem;
    private List<HProblem> problemList;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
        binder.registerCustomEditor(Integer.class, new EMRMSCustomEditor());
    }

    @RequestMapping(value = "/charting", method = RequestMethod.GET)
    public ModelAndView showCharting(HttpServletRequest request, HttpServletResponse response) {
        sessionHelper.setActiveChartingTab("allergies");
        setupMav(request);
        return mav;
    }

    private void setupMav(HttpServletRequest request) {
        mav = new ModelAndView("chartingTabShell");
        session = request.getSession(false);
        patientId = sessionHelper.getHPatientId(session);
        sessionHelper.setActivePatient(patientId);

        determineCurrentEncounter();

        mav.addObject("showHeader", true);
        mav.addObject("siteHeader", sessionHelper.getSiteHeader());

        addAllergiesToMav();
        addDiagnosesToMav();
        addAssessmentsToMav();
        addProblemsToMav();

        mav = sessionHelper.addSessionHelperAttributes(mav);
    }

    private void determineCurrentEncounter() {
        try {
            String currentEncounterId = sessionHelper.getSiteHeader().getEncounterNumber();

            currentEncounter = patientDemographicsService.getPatientEncounters(patientId).stream()
                    .filter(matchingEncounter -> matchingEncounter.getEncounterID().equals(currentEncounterId))
                    .findFirst()
                    .orElseThrow(NullPointerException::new);

            mav.addObject("currentEncounter", currentEncounter);
        } catch (Exception ex) {
            currentEncounter = null;
        }

        mav.addObject("showAddAction", currentEncounter != null);
    }

    private void addAllergiesToMav() {
        newAllergy = new HAllergy();

        try {
            allergyList = patientDemographicsService.getPatientAllergies(sessionHelper.getPatientId());
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

        setupMav(request);

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

        setupMav(request);

        return mav;
    }

    private void addDiagnosesToMav() {
        newDiagnosis = new HDiagnosis();

        try {
            newDiagnosis.setUserId(sessionHelper.getApplicationUser(session));
            newDiagnosis.setPatientID(sessionHelper.getPatientId());
            final int currentEncounterId = currentEncounter.getHEncounterID();
            diagnosisList = patientDemographicsService.getPatientDiagnoses(patientId).stream()
                    .filter(diagnosis -> diagnosis.getEncounterID() == currentEncounterId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            diagnosisList = new ArrayList<>();
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
        newDiagnosis.setEncounterID(currentEncounter.getHEncounterID());

        manageDiagnosisService.AddDiagnosis(newDiagnosis);

        setupMav(request);

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

        setupMav(request);

        return mav;
    }

    private void addAssessmentsToMav() {
        newAssessment = new HAssessment();

        try {
            sessionHelper.getPatientId();
            int encounterObjectId = currentEncounter.getHEncounterID();
            assessmentList = manageAssessmentService.GetPatientAssessments(patientId, encounterObjectId);
            newAssessment.setEncounterObjectId(encounterObjectId);
        } catch (Exception e) {
            assessmentList = new ArrayList<>();
        }

        newAssessment.setUserId(sessionHelper.getApplicationUser(session));

        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientId);

        if (encounters != null && !encounters.isEmpty()) {
            newAssessment.setEncounterObjectId(encounters.get(0).getHEncounterID());
        }

        mav.addObject("assessmentList", assessmentList);
        mav.addObject("newAssessment", newAssessment);
        mav.addObject("deletedAssessment", new HAssessment());
    }

    @RequestMapping(value = "/addAssessment", method = RequestMethod.POST)
    public ModelAndView addAssessment(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("newAssessment") HAssessment assessment, BindingResult bindingResult) throws PatientNotFoundException {
        sessionHelper.setActiveChartingTab("assessments");

        final HttpSession session = request.getSession();

        assessment.setUserId(sessionHelper.getApplicationUser(session));
        assessment.setEncounterObjectId(currentEncounter.getHEncounterID());
        assessment.setPatientObjectId(sessionHelper.getPatientId());
        assessment.setCollectedDateTime(Date.valueOf(LocalDate.now()).toString());
        assessment.setHeightmeasureId(1);
        assessment.setWeightmeasureId(3);
        assessment.setTemperaturemeasureId(5);

        manageAssessmentService.AddAssessment(assessment);

        setupMav(request);
        return mav;
    }

    @RequestMapping(value = "/deleteAssessment", method = RequestMethod.POST)
    public ModelAndView deleteAssessment(HttpServletRequest request, HttpServletResponse response,
                                         @ModelAttribute("deletedAssessment") HAssessment deletedAssessment, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("assessments");

        try {
            final int deletedAssessmentId = deletedAssessment.getObjectId();

            deletedAssessment = assessmentList.stream()
                    .filter(assessment -> assessment.getObjectId() == deletedAssessmentId)
                    .findFirst()
                    .get();

            chartingMapper.deleteAssessment(deletedAssessment);
        } catch (Exception e) {
            // Fine
        }

        setupMav(request);
        return mav;
    }

    private void addProblemsToMav() {
        newProblem = new HProblem();

        try {
            sessionHelper.getPatientId();
            int encounterObjectId = currentEncounter.getHEncounterID();
            problemList = manageProblemService.GetProblemsList(patientId, encounterObjectId);
            newProblem.setEncObjectId(encounterObjectId);
        } catch (Exception e) {
            problemList = new ArrayList<>();
        }

        newProblem.setUserId(sessionHelper.getApplicationUser(session));

        List<HEncounter> encounters = patientDemographicsMapper.getPatientEncounters(patientId);

        if (encounters != null && !encounters.isEmpty()) {
            newProblem.setEncObjectId(encounters.get(0).getHEncounterID());
        }

        mav.addObject("problemList", problemList);
        mav.addObject("newProblem", newProblem);
        mav.addObject("deletedProblem", new HProblem());
    }

    @RequestMapping(value = "/addProblem", method = RequestMethod.POST)
    public ModelAndView addProblem(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("newProblem") HProblem problem, BindingResult bindingResult) throws PatientNotFoundException {
        sessionHelper.setActiveChartingTab("problems");

        final HttpSession session = request.getSession();

        problem.setUserId(sessionHelper.getApplicationUser(session));
        problem.setEncObjectId(currentEncounter.getHEncounterID());
        problem.setPatientID(sessionHelper.getPatientId());
        problem.setCreationDateTime(Date.valueOf(LocalDate.now()).toString());

        manageProblemService.AddProblem(problem);

        setupMav(request);
        return mav;
    }

    @RequestMapping(value = "/deleteProblem", method = RequestMethod.POST)
    public ModelAndView deleteProblem(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("deletedProblem") HProblem deletedProblem, BindingResult bindingResult) {
        sessionHelper.setActiveChartingTab("problems");

        try {
            final int deletedProblemId = deletedProblem.getObjectId();

            deletedProblem = problemList.stream()
                    .filter(assessment -> assessment.getObjectId() == deletedProblemId)
                    .findFirst()
                    .get();

            chartingMapper.deleteProblem(deletedProblem);
        } catch (Exception e) {
            // Fine
        }

        setupMav(request);
        return mav;
    }
}
