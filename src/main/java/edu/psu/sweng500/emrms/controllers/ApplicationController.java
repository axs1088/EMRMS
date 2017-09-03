package edu.psu.sweng500.emrms.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import edu.psu.sweng500.emrms.validators.EMRMSBindingErrorProcessor;

/**
 * The Class ApplicationController.
 */
@Controller
public class ApplicationController {


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

		ModelAndView model = new ModelAndView("hello");
		model.addObject("msg", "hello collaborators, welcome to the party!");

		return model;
	}

}
