package com.pennmutual.preference.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pennmutual.preference.models.AssociationObject;
import com.pennmutual.preference.models.EliteProducer;
import com.pennmutual.preference.models.PolicyPrefView;
import com.pennmutual.preference.models.Scorecard;
import com.pennmutual.preference.models.SearchResult;
import com.pennmutual.preference.models.SpecialLevel;
import com.pennmutual.preference.models.UWGroup;
import com.pennmutual.preference.models.UWLevel;
import com.pennmutual.preference.models.UWOffice;
import com.pennmutual.preference.models.UnderwriterAssignedPolicyView;
import com.pennmutual.preference.models.UnderwriterView;
import com.pennmutual.preference.models.UpdateResult;
import com.pennmutual.preference.util.SSOUtils;
import com.pennmutual.preference.validators.PreferenceBindingErrorProcessor;
import com.pennmutual.preference.wsclient.UWServiceClient;
import com.pennmutual.preference.wsclient.response.UWServiceResponse;

/**
 * The Class ApplicationController.
 */
@Controller
public class ApplicationController {

    /** The underwriter service. */
    @Autowired
    private UWServiceClient underwriterService;

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     * 
     * @param binder
     *            the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new PreferenceBindingErrorProcessor());
    }

    /**
     * Load underwriter assignments.
     * 
     * @return the list
     */
    @RequestMapping(value = "/assignmentPageOnLoad", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult loadUWAssignments(HttpSession session, HttpServletRequest request) {

        List<UnderwriterView> underwriterViews = null;

        UWServiceResponse serviceResponse = underwriterService != null ? underwriterService.getListOfUWViews(request) : new UWServiceResponse();
        underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Load policy preferences.
     * 
     * @return the list
     */
    @RequestMapping(value = "/policyPrefPageOnLoad", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult loadPolicyPreferences(HttpSession session, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfPolicyPrefs(httpServletRequest) : new UWServiceResponse();

        List<UnderwriterView> underwriters = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriters);
    }

    /**
     * Load list of underwriters.
     * 
     * @return the list
     */
    @RequestMapping(value = "/underwritersPageOnLoad", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult loadUnderwriters(HttpSession session, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUnderwriters(httpServletRequest) : new UWServiceResponse();
        List<PolicyPrefView> policyPrefsViews = (List<PolicyPrefView>) serviceResponse.getData();
        return new SearchResult(serviceResponse.getErrorObject(), policyPrefsViews);
    }

    /**
     * Search by underwriter info such as group code and/or level code.
     * 
     * @param groupCode
     *            the underwriter group code
     * @param levelCode
     *            the underwriter level code
     * @return the list
     */
    @RequestMapping(value = "/uwassignments", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByUnderwriterInfo(HttpSession session, @RequestParam(value = "groupCode", defaultValue = "") String groupCode,
            @RequestParam(value = "levelCode", defaultValue = "") String levelCode, HttpServletRequest httpServletRequest) {

        List<UnderwriterView> underwriterViews = null;
        UWServiceResponse serviceResponse = null;

        if (!StringUtils.isEmpty(groupCode) && StringUtils.isEmpty(levelCode)) {
            serviceResponse =
                    underwriterService != null ? underwriterService.getListOfUWViewsByGroup(groupCode, httpServletRequest) : new UWServiceResponse();
        } else if (StringUtils.isEmpty(groupCode) && !StringUtils.isEmpty(levelCode)) {
            serviceResponse =
                    underwriterService != null ? underwriterService.getListOfUWViewsByLevel(levelCode, httpServletRequest) : new UWServiceResponse();
        } else if (!StringUtils.isEmpty(groupCode) && !StringUtils.isEmpty(levelCode)) {
            serviceResponse =
                    underwriterService != null ? underwriterService.getListOfUWViewsByGroupAndLevel(groupCode, levelCode, httpServletRequest)
                            : new UWServiceResponse();
        } else {
            serviceResponse = new UWServiceResponse();
        }

        underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Search by policy number.
     * 
     * @param polNumber
     *            the policy number
     * @return the list
     */
    @RequestMapping(value = "/bypolicy", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByPolicy(HttpSession session, @RequestParam(value = "polNumber", defaultValue = "") String polNumber,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfPrefsByPolicy(polNumber, httpServletRequest) : new UWServiceResponse();
        List<PolicyPrefView> policyPrefViews = (List<PolicyPrefView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), policyPrefViews);
    }

    /**
     * Search by elite status and/or number of days.
     * 
     * @param days
     *            the number of days
     * @param eliteStatus
     *            the elite status
     * @return the list
     */
    @RequestMapping(value = "/bydayselitestatus", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByDaysEliteStatus(HttpSession session, @RequestParam(value = "days", defaultValue = "") String days, @RequestParam(
            value = "eliteStatus", defaultValue = "") String eliteStatus, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfPrefsByDaysEliteStatus(days, eliteStatus, httpServletRequest)
                        : new UWServiceResponse();
        List<PolicyPrefView> policyPrefViews = (List<PolicyPrefView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), policyPrefViews);
    }

    /**
     * Search by ace status and/or number of days.
     * 
     * @param days
     *            the number of days
     * @param aceStatus
     *            the ace status
     * @return the list
     */
    @RequestMapping(value = "/bydaysacestatus", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByDaysACEStatus(HttpSession session, @RequestParam(value = "days", defaultValue = "") String days, @RequestParam(
            value = "aceStatus", defaultValue = "") String aceStatus, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfPrefsByDaysACEStatus(days, aceStatus, httpServletRequest)
                        : new UWServiceResponse();
        List<PolicyPrefView> policyPrefViews = (List<PolicyPrefView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), policyPrefViews);
    }    
    
    /**
     * Update policy preferences by policy.
     * 
     * @param policyPrefView
     *            the policy preferences object
     * @return the update response
     */
    @RequestMapping(value = "/updatepolicypreferences", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult updatePolicyPreferences(@RequestBody PolicyPrefView policyPrefView, HttpServletRequest request) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.updatePolicyPreferences(policyPrefView, request) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/policies/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult underwriterPolicyPreferences(@PathVariable(value = "id") String id,
            @RequestParam(value = "duration", defaultValue = "") String duration, HttpServletRequest request) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUWPolicyPreferences(id, duration, request) : new UWServiceResponse();
        List<UnderwriterAssignedPolicyView> view = (List<UnderwriterAssignedPolicyView>) serviceResponse.getData();
        return new SearchResult(serviceResponse.getErrorObject(), view);
    }

    @RequestMapping(value = "/policies/{id}/{group}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult underwriterPolicyPreferencesByGroup(@PathVariable(value = "id") String id, @PathVariable(value = "group") String group,
            @RequestParam(value = "duration", defaultValue = "") String duration, HttpServletRequest request) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUWPolicyPreferences(id, group, duration, request) : new UWServiceResponse();
        List<UnderwriterAssignedPolicyView> view = (List<UnderwriterAssignedPolicyView>) serviceResponse.getData();
        return new SearchResult(serviceResponse.getErrorObject(), view);
    }

    /**
     * Search by active status.
     * 
     * @param activeStatus
     *            the active status
     * @return the list
     */
    @RequestMapping(value = "/byactivestatus", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByActiveStatus(HttpSession session, @RequestParam(value = "activeStatus", defaultValue = "") String activeStatus,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUWByActiveStatus(activeStatus, httpServletRequest) : new UWServiceResponse();
        List<UnderwriterView> underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Search by out of office status.
     * 
     * @param outOfOfficeStatus
     *            the out of office status
     * @return the list
     */
    @RequestMapping(value = "/byoutofofficeStatus", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchByOutOfOfficeStatus(HttpSession session,
            @RequestParam(value = "outOfOfficeStatus", defaultValue = "") String outOfOfficeStatus, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUWByOutOfOfficeStatus(outOfOfficeStatus, httpServletRequest)
                        : new UWServiceResponse();
        List<UnderwriterView> underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    @RequestMapping(value = "/byActiveAndOoo", method = RequestMethod.GET)
    // TODO NOT WORKING YET
            @ResponseBody
            public
            SearchResult searchByActiveAndOoo(HttpSession session, @RequestParam(value = "activeStatus", defaultValue = "") String activeStatus,
                    @RequestParam(value = "outOfOfficeStatus", defaultValue = "") String outOfOfficeStatus, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService
                        .getListOfUWByActiveAndOutOfOffice(activeStatus, outOfOfficeStatus, httpServletRequest) : new UWServiceResponse();
        List<UnderwriterView> underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Search by underwriter name.
     * 
     * @param lastName
     *            the last name
     * @param firstName
     *            the first name
     * @return the list
     */
    @RequestMapping(value = "/byunderwritername", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchUnderwriterName(HttpSession session, @RequestParam(value = "lastName", defaultValue = "") String lastName,
            @RequestParam(value = "firstName", defaultValue = "") String firstName, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUWByName(lastName, firstName, httpServletRequest) : new UWServiceResponse();
        List<UnderwriterView> underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Search by underwriter id.
     * 
     * @param underwriterId
     *            the underwriter id
     * @return the underwriter
     */
    @RequestMapping(value = "/byunderwriterid", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult searchUnderwriterId(HttpSession session, @RequestParam(value = "underwriterId", defaultValue = "") String underwriterId,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUnderwriterById(underwriterId, httpServletRequest) : new UWServiceResponse();
        List<UnderwriterView> underwriterViews = (List<UnderwriterView>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterViews);
    }

    /**
     * Deletes the underwriter
     * 
     * @param underwriterId
     *            the underwriter id
     * @return response
     */
    @RequestMapping(value = "/deleteunderwriter", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult deleteUnderwriter(HttpSession session, @RequestParam(value = "id", defaultValue = "") String underwriterId,
            HttpServletRequest httpServletRequest, HttpServletResponse response) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.deleteUnderwriterById(underwriterId, httpServletRequest, response)
                        : new UWServiceResponse();
        /*
         * Enumeration<String> headers = httpServletRequest.getHeaderNames();
         * while (headers.hasMoreElements()) {
         * String element = headers.nextElement();
         * response.addHeader(element, httpServletRequest.getHeader(element));
         * }
         */
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Updates the underwriter
     * 
     * @return result
     */
    @RequestMapping(value = "/updateunderwriter", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult updateUnderwriter(@RequestBody UnderwriterView underwriterView, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.updateUnderwriter(underwriterView, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Adds the underwriter
     * 
     * @return result
     */
    @RequestMapping(value = "/addunderwriter", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult addUnderwriter(@RequestBody UnderwriterView underwriterView, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.addUnderwriter(underwriterView, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Breaks underwriter association
     */
    @RequestMapping(value = "/breakuwassociation/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult breakUWAssociation(HttpSession session, @PathVariable(value = "id") String id, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.breakUWAssociation(id, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Edits the underwriter association
     * 
     * @return result
     */
    @RequestMapping(value = "/editassociation", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult editAssociation(@RequestBody UnderwriterView underwriterView, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editAssociation(underwriterView, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * gets uw Levels
     * 
     * @return the uwLevels
     */
    @RequestMapping(value = "/uwlevels", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwLevels(HttpSession session, HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUWLevels(httpServletRequest) : new UWServiceResponse();
        List<UWLevel> underwriterLevels = (List<UWLevel>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterLevels);
    }

    /**
     * gets specified uw Levels
     * 
     * @return the uwLevels
     */
    @RequestMapping(value = "/uwlevels/{code}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwLevel(HttpSession session, HttpServletRequest httpServletRequest, @PathVariable(value = "code") String code) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUWLevel(httpServletRequest, code) : new UWServiceResponse();
        List<UWLevel> underwriterLevels = (List<UWLevel>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterLevels);
    }

    @RequestMapping(value = "/uwlevels/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult deleteUWLevel(HttpSession session, HttpServletRequest httpServletRequest, @PathVariable(value = "code") String code) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.deleteLevelByCode(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/uwlevels", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult editUwLevel(@RequestBody UWLevel level, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editUWLevel(level, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/uwlevels", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult addUwLevel(@RequestBody UWLevel level, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.addUWLevel(level, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * 
     * 
     * @return the uwGroups
     */
    @RequestMapping(value = "/uwgroup", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwGroup(HttpSession session, @RequestParam(value = "active", defaultValue = "") String active,
            HttpServletRequest httpServletRequest) {
        if (!active.equals("")) {
            UWServiceResponse serviceResponse =
                    underwriterService != null ? underwriterService.getListOfGroupsByStatus(active, httpServletRequest) : new UWServiceResponse();
            List<UWGroup> underwriterGroups = (List<UWGroup>) serviceResponse.getData();

            return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
        }
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getListOfUWGroups(httpServletRequest) : new UWServiceResponse();
        List<UWGroup> underwriterGroups = (List<UWGroup>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
    }

    /**
     * 
     * 
     * @return the uwGroups
     */
    @RequestMapping(value = "/uwgroups", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwGroups(HttpSession session, @RequestParam(value = "active", defaultValue = "") String active,
            HttpServletRequest httpServletRequest) {
        if (!active.equals("")) {
            UWServiceResponse serviceResponse =
                    underwriterService != null ? underwriterService.getListOfGroupsByStatus(active, httpServletRequest) : new UWServiceResponse();
            List<UWGroup> underwriterGroups = (List<UWGroup>) serviceResponse.getData();

            return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
        }
        UWServiceResponse serviceResponse = underwriterService != null ? underwriterService.getUWGroups(httpServletRequest) : new UWServiceResponse();
        List<UWGroup> underwriterGroups = (List<UWGroup>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
    }

    @RequestMapping(value = "/uwgroups/{code}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwGroupByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getGroupByCode(code, httpServletRequest) : new UWServiceResponse();
        List<UWGroup> underwriterGroups = (List<UWGroup>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
    }

    @RequestMapping(value = "/uwgroups/{code}/association", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult uwGroupAssociation(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getGroupAssociation(code, httpServletRequest) : new UWServiceResponse();
        List<AssociationObject> associations = (List<AssociationObject>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), associations);
    }

    @RequestMapping(value = "/uwgroups/{code}/association", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult editAssociation(@RequestBody AssociationObject association, @PathVariable(value = "code") String code,
            HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editUWGroupAssociation(code, association, httpServletRequest)
                        : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/uwgroups/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult deleteUWGroupByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.deleteGroupByCode(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());

    }

    @RequestMapping(value = "/uwgroups/special/level", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getSpecialLevel(HttpSession session, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getSpecialLevel(httpServletRequest) : new UWServiceResponse();
        List<SpecialLevel> specialLevel = (List<SpecialLevel>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), specialLevel);
    }

    @RequestMapping(value = "/breakuwgroupassociation/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult
            breakUWGroupAssociation(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.breakGroupAssociation(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/adduwgroup", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult addUwGroup(@RequestBody UWGroup uwGroup, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.addUWGroup(uwGroup, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/edituwgroup", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult editUwGroup(@RequestBody UWGroup uwGroup, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editUWGroup(uwGroup, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/office", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult office(HttpSession session, @RequestParam(value = "active", defaultValue = "") String active,
            HttpServletRequest httpServletRequest) {
        if (!active.equals("")) {
            UWServiceResponse serviceResponse =
                    underwriterService != null ? underwriterService.getListOfOfficesByStatus(active, httpServletRequest) : new UWServiceResponse();
            List<UWOffice> offices = (List<UWOffice>) serviceResponse.getData();

            return new SearchResult(serviceResponse.getErrorObject(), offices);
        }
        UWServiceResponse serviceResponse = underwriterService != null ? underwriterService.getOffices(httpServletRequest) : new UWServiceResponse();
        List<UWOffice> offices = (List<UWOffice>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), offices);
    }

    /**
     * Gets office
     */
    @RequestMapping(value = "/office/{code}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getOfficeByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getOfficeByCode(code, httpServletRequest) : new UWServiceResponse();
        List<UWOffice> underwriterGroups = (List<UWOffice>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);
    }

    /**
     * Deletes office
     */
    @RequestMapping(value = "/office/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult deleteOfficeByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.deleteOfficeByCode(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Updates Office Association
     */
    @RequestMapping(value = "/office/{code}/association", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult officeAssociation(@RequestBody UWOffice office, @PathVariable(value = "code") String code,
            HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.officeAssociation(code, office, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Breaks office association
     */
    @RequestMapping(value = "/breakofficeassociation/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult breakOfficeAssociation(@PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.breakOfficeAssociation(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Adds office
     */
    @RequestMapping(value = "/addoffice", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult addOffice(@RequestBody UWOffice office, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.addOffice(office, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Edits selected office
     */
    @RequestMapping(value = "/editoffice", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult editOffice(@RequestBody UWOffice office, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editOffice(office, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Gets Unassigned Offices
     */
    @RequestMapping(value = "/unassignedoffice", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult unassigned(HttpSession session, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUnassigned(httpServletRequest) : new UWServiceResponse();
        List<UWOffice> underwriterGroups = (List<UWOffice>) serviceResponse.getData();
        return new SearchResult(serviceResponse.getErrorObject(), underwriterGroups);

    }

    /**
     * Gets associations
     */
    @RequestMapping(value = "/associations", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult associations(HttpSession session, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getAssociations(httpServletRequest) : new UWServiceResponse();
        List<AssociationObject> associations = (List<AssociationObject>) serviceResponse.getData();
        return new SearchResult(serviceResponse.getErrorObject(), associations);
    }

    /**
     * Gets Elite Producers
     */
    @RequestMapping(value = "/eliteproducers", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult elite(HttpSession session, @RequestParam(value = "active", defaultValue = "") String active,
            HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getEliteProducersByStatus(active, httpServletRequest) : new UWServiceResponse();
        List<EliteProducer> associations = (List<EliteProducer>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), associations);
    }

    /**
     * Adds Elite Producer
     */
    @RequestMapping(value = "/eliteproducers", method = RequestMethod.POST)
    @ResponseBody
    public UpdateResult addElite(@RequestBody EliteProducer ep, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.addElite(ep, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Edits Elite Producer
     */
    @RequestMapping(value = "/eliteproducers", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult editElite(@RequestBody EliteProducer ep, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.editElite(ep, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Gets Elite Producer by Code
     */
    @RequestMapping(value = "/eliteproducers/{code}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult eliteByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getEliteProducersByCode(code, httpServletRequest) : new UWServiceResponse();
        List<EliteProducer> e = (List<EliteProducer>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), e);
    }

    /**
     * Deletes Elite Producer
     */
    @RequestMapping(value = "/eliteproducers/{code}", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult deleteEliteByCode(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse = underwriterService.deleteEliteProducer(code, httpServletRequest);
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Edits Elite Producer association
     */
    @RequestMapping(value = "/eliteproducers/{code}/association", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateResult manageEliteAssociation(HttpSession session, @PathVariable(value = "code") String code, @RequestBody EliteProducer ep,
            HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.manageEliteAssociation(ep, code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    /**
     * Breaks Elite Producer association
     */
    @RequestMapping(value = "/eliteproducers/{code}/association", method = RequestMethod.DELETE)
    @ResponseBody
    public UpdateResult manageEliteAssociation(HttpSession session, @PathVariable(value = "code") String code, HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.breakEliteAssociation(code, httpServletRequest) : new UWServiceResponse();
        return new UpdateResult(serviceResponse.getErrorObject(), serviceResponse.getStatusCode());
    }

    @RequestMapping(value = "/scorecards", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getScorecards(HttpSession session, @RequestParam(value = "duration", defaultValue = "7") String duration,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUwScorecards(duration, httpServletRequest) : new UWServiceResponse();
        List<Scorecard> scoreCards = (List<Scorecard>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), scoreCards);
    }

    @RequestMapping(value = "/scorecards/uw/{uw}", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getScorecardsByUw(HttpSession session, @RequestParam(value = "duration", defaultValue = "7") String duration,
            @RequestParam(value = "decisionstatus", defaultValue = "") String decisionstatus, 
            @RequestParam(value = "showpolicies", defaultValue = "no") String showpolicies, @PathVariable(value = "uw") String uw,
            HttpServletRequest httpServletRequest) {
        UWServiceResponse serviceResponse;

        serviceResponse =
                underwriterService != null ? underwriterService.getUwScorecardsByUwAndStatus(duration, decisionstatus, showpolicies,uw, httpServletRequest) : new UWServiceResponse();

        List<Scorecard> scoreCards = (List<Scorecard>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), scoreCards);
    }
    
    @RequestMapping(value = "/scorecards/uwdecisiondays", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getScorecardAvgDays(HttpSession session, @RequestParam(value = "duration", defaultValue = "7") String duration,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getUWDecisionDays(duration, httpServletRequest) : new UWServiceResponse();
        List<Scorecard> scoreCards = (List<Scorecard>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), scoreCards);
    }
    
    @RequestMapping(value = "/scorecards/policytimeseries", method = RequestMethod.GET)
    @ResponseBody
    public SearchResult getPolicyTimeseries(HttpSession session, @RequestParam(value = "duration", defaultValue = "7") String duration,
            HttpServletRequest httpServletRequest) {

        UWServiceResponse serviceResponse =
                underwriterService != null ? underwriterService.getTimeSeries(duration, httpServletRequest) : new UWServiceResponse();
        List<Scorecard> scoreCards = (List<Scorecard>) serviceResponse.getData();

        return new SearchResult(serviceResponse.getErrorObject(), scoreCards);
    }

    /*
     * Get satoken if debugging on dev env
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        InputStream s = getClass().getResourceAsStream("/preference.properties");
        Properties p = new Properties();
        try {
            p.load(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = p.getProperty("debug");
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (str != null && str.equals("true"))?"{\"satoken\":\""+SSOUtils.getSSOToken(httpServletRequest)+"\"}":"";
    }

    /**
     * Gets the underwriterService
     * 
     * @return the underwriterService
     */
    public UWServiceClient getUnderwriterService() {
        return underwriterService;
    }

    /**
     * Sets the underwriterService
     * 
     * @param underwriterService
     *            the underwriterService to set
     */
    public void setUnderwriterService(UWServiceClient underwriterService) {
        this.underwriterService = underwriterService;
    }

}
