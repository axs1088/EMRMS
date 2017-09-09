package edu.psu.sweng500.emrms.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.psu.sweng500.emrms.model.HPerson;
import edu.psu.sweng500.emrms.service.UserService;
import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;

/**
 * The Class ApplicationController.
 */
@Controller
public class ApplicationController {


	@Autowired
	private UserService userService;

	
    /**
     * Initialize data binder. Support MM/dd/yyyy dates.
     * 
     * @param binder
     *            the binder to initialize
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new EMRMSBindingErrorProcessor());
    }
    
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

    	HPerson person = userService.getUserDetails();
    	
		ModelAndView model = new ModelAndView("hello");
		if (person != null) {
			model.addObject("msg", person.getPersonId() + " " + person.getGender());
		}
		
		HPerson person1 = new HPerson();
		person1.setPersonId(3);
		person1.setUserId("TestUser");
		person1.setGender(1);
		person1.setRace("Asian");
		
		userService.insertUserDetails(person1);

		return model;
	}
    
}
