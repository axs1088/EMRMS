package edu.psu.sweng500.emrms.application;

import edu.psu.sweng500.emrms.controllers.annotation.ApplicationFormController;
import edu.psu.sweng500.emrms.mappers.PatientDemographicsMapper;
import edu.psu.sweng500.emrms.model.HEncounter;
import edu.psu.sweng500.emrms.model.HName;
import edu.psu.sweng500.emrms.model.SiteHeader;
import edu.psu.sweng500.emrms.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Methods for synching the session with an application.
 */
@Component
public class ApplicationSessionHelper {

    /**
     * The log.
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * The session attributes.
     */
    private Set<String> sessionAttributes;

    /**
     * The bean factory.
     */
    @Autowired
    public ListableBeanFactory beanFactory;

    /**
     * The message source.
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PatientDemographicsMapper patientDemographicsMapper;

    private Integer patientId;

    /**
     * Gets the session attributes for all application controllers.
     *
     * @return the session attribute names
     */
    public Set<String> getAllSessionAttributes() {
        // Generate the session attributes on demand.
        // We can't do it on instantiation because we have to wait for all the controllers
        // to be autowired first.
        if (sessionAttributes == null) {
            // Grab all the session attributes used by our controllers from their annotations.
            sessionAttributes = new HashSet<String>();
            for (Object controller : beanFactory.getBeansWithAnnotation(ApplicationFormController.class).values()) {
                SessionAttributes controllerAttributes = AnnotationUtils.findAnnotation(controller.getClass(), SessionAttributes.class);
                if (controllerAttributes != null) {
                    for (String attribute : controllerAttributes.value()) {
                        sessionAttributes.add(attribute);
                    }
                }
            }
        }
        return sessionAttributes;
    }

    /**
     * Gets the logged in user login id.
     *
     * @param session the current session
     * @return the logged in user login id, or null if not applicable
     */
    public String getApplicationUser(HttpSession session) {
        return (String) session.getAttribute(Constants.APP_USER_LOGIN_ID);
    }

    /**
     * Gets the logged in user id.
     *
     * @param session the current session
     * @return the logged in user id, or null if not applicable
     */
    public Integer getApplicationUserId(HttpSession session) {
        return (Integer) session.getAttribute(Constants.APP_USER_ID);
    }

    /**
     * Gets the logged in user type.
     *
     * @param session the current session
     * @return the logged in user type, or null if not applicable
     */
    public Long getApplicationUserType(HttpSession session) {
        return (Long) session.getAttribute(Constants.APP_USER_TYPE);
    }

    /**
     * Gets the bean factory.
     *
     * @return the bean factory
     */
    public ListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * Sets the bean factory.
     *
     * @param beanFactory the new bean factory
     */
    public void setBeanFactory(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * Gets the message source.
     *
     * @return the message source
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Sets the message source.
     *
     * @param messageSource the new message source
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Clear all session attributes for the application.
     *
     * @param session the session
     */
    public void clearAllSessionAttributes(HttpSession session) {
        for (String attribute : getAllSessionAttributes()) {
            session.removeAttribute(attribute);
        }
        session.removeAttribute(Constants.APP_USER_ID);
        session.removeAttribute(Constants.APP_USER_LOGIN_ID);
        session.removeAttribute(Constants.APP_USER_TYPE);
        session.removeAttribute(Constants.AUTHORIZED_FOR_EMRMS);

        patientId = null;
    }

    public void setActivePatient(Integer patientId) {
        this.patientId = patientId;
    }

    public SiteHeader getSiteHeader() {
        SiteHeader siteHeader = new SiteHeader();

        if (patientId == null) {
            return siteHeader;
        }

        List<String> severeAllergies = getSevereAllergies();
        if (severeAllergies.isEmpty()) {
            siteHeader.setIgnoredSelectedAllergy("");
        } else {
            siteHeader.setIgnoredSelectedAllergy(severeAllergies.get(0));
        }

        List<String> primaryDiagnoses = getPrimaryDiagnoses();
        if (primaryDiagnoses.isEmpty()) {
            siteHeader.setIgnoredSelectedDiagnosis("");
        } else {
            siteHeader.setIgnoredSelectedDiagnosis(primaryDiagnoses.get(0));
        }

        try {
            HEncounter encounter = patientDemographicsMapper.getPatientEncounters(patientId).get(0);
            siteHeader.setEncounterType(encounter.getEncounterType());
            siteHeader.setEncounterStartDate(encounter.getEncStartDateTime());
            siteHeader.setEncounterStatus(String.valueOf(encounter.getEncStatus()));
            siteHeader.setEncounterNumber(encounter.getEncounterID());
            siteHeader.setAttending("Doctor #" + encounter.getAttendingPhysician_ObjectID());
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            siteHeader.setEncounterType("");
            siteHeader.setEncounterStartDate("");
            siteHeader.setEncounterStatus("");
            siteHeader.setEncounterNumber("");
            siteHeader.setAttending("");
        }

        int personId = patientDemographicsMapper.getPatientDetails(patientId).getPersonId();
        HName personName = patientDemographicsMapper.getPersonName(personId);
        siteHeader.setNameLastCommaFirst(HName.getLastCommaFirstMiddleInitial(personName));

        try {
            long mrNumber = patientDemographicsMapper.getPatientIdentifiers(patientId).get(0).getPatientId();
            siteHeader.setMrNumber(String.valueOf(mrNumber));
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            siteHeader.setMrNumber("NULL");
        }

        try {
            siteHeader.setBirthDate(patientDemographicsMapper
                    .getPersonDetails(patientId)
                    .getBirthDate()
                    .toString());
        } catch (NullPointerException ex) {
            siteHeader.setBirthDate("NULL");
        }

        return siteHeader;
    }

    private List<String> stringListWithOnlyNoneElement() {
        ArrayList<String> returnValue = new ArrayList<>();
        returnValue.add("none");
        return returnValue;
    }

    public List<String> getSevereAllergies() {
        if (patientId == null) {
            return new ArrayList<>();
        }

        List<String> returnList = patientDemographicsMapper.getPatientAllergies(patientId)
                .stream()
                .filter(hAllergy -> hAllergy.getSeverity().equalsIgnoreCase("severe"))
                .map(allergyName -> allergyName.getAllergyName())
                .collect(Collectors.toList());

        if (returnList.isEmpty()) {
            return stringListWithOnlyNoneElement();
        }

        return returnList;
    }

    public List<String> getPrimaryDiagnoses() {
        if (patientId == null) {
            return new ArrayList<>();
        }

        List<String> returnList = patientDemographicsMapper.getPatientDiagnoses(patientId)
                .stream()
                .filter(hDiagnosis -> hDiagnosis.getPriority() == 1)
                .map(diagnosisName -> diagnosisName.getDescription())
                .collect(Collectors.toList());

        if (returnList.isEmpty()) {
            return stringListWithOnlyNoneElement();
        }

        return returnList;

    }

    public String getPhysicianName() {
        if (patientId == null) {
            return "(no patient)";
        }

        try {
            int hStaffId = patientDemographicsMapper.getPatientEncounters(patientId).get(0).getAttendingPhysician_ObjectID();
            return "Doctor #" + hStaffId;
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            return "NULL";
        }
    }

    public String getClinicName() {
        if (patientId == null) {
            return "";
        }

        try {
            int hClinicId = patientDemographicsMapper.getPatientEncounters(patientId).get(0).getEncounterLocation_ObjectID();
            return "Clinic Location #" + hClinicId;
        } catch (NullPointerException | IndexOutOfBoundsException ex) {
            return "NULL";
        }
    }
}
