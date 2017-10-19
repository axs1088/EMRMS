package edu.psu.sweng500.emrms.application;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.psu.sweng500.emrms.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationSecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request,
    						 HttpServletResponse response,
    						 Object handler) throws Exception {

		Boolean authorizedForEMRMS = null;
		HttpSession session = request.getSession(false);

		if (session != null) {
        	authorizedForEMRMS = (Boolean) session.getAttribute(Constants.AUTHORIZED_FOR_EMRMS);
		}

		if (authorizedForEMRMS==null) {
			authorizedForEMRMS=false;
		}
		if(session != null) {
			session.setAttribute(Constants.AUTHORIZED_FOR_EMRMS,  authorizedForEMRMS);
		}

		if(!authorizedForEMRMS) {
			response.sendRedirect("home");
			return false;
		}

    	return true;
    }

}
