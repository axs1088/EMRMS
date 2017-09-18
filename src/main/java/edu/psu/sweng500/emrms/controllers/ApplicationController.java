package edu.psu.sweng500.emrms.controllers;

import edu.psu.sweng500.emrms.model.HCensus;
import edu.psu.sweng500.emrms.service.CensusService;
import edu.psu.sweng500.emrms.service.UserService;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The Class ApplicationController.
 */
@Controller
public class ApplicationController {
    @Autowired
    private UserService userService;

    @Autowired
    private CensusService censusService;

    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     *
     * @param binder the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	/*
        List<HPerson> personList = userService.getUserDetails();
    	
		ModelAndView model = new ModelAndView("hello");
		if (CollectionUtils.isNotEmpty(personList)) {
			model.addObject("msg", personList.get(0).getPersonId() + " " + personList.get(0).getGender());
		}
		
		HPerson person1 = new HPerson();
		person1.setPersonId(3);
		person1.setUserId("TestUser");
		person1.setGender(1);
		person1.setRace("Asian");
		
		userService.insertUserDetails(person1);*/

        List<HCensus> hCensusList = censusService.getPhysicianCensus(1);

        ModelAndView model = new ModelAndView("hello");

        if (CollectionUtils.isNotEmpty(hCensusList)) {
            model.addObject("msg", generateWelcomeMessage(hCensusList.get(0)));
        }

        return model;
    }

    public static String generateWelcomeMessage(HCensus validCensus) {
        return "Welcome : " + validCensus.getFirstName() + " " + validCensus.getLastName();
    }
}
