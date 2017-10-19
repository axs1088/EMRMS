package edu.psu.sweng500.emrms.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.psu.sweng500.emrms.controllers.annotation.ApplicationFormController;
import edu.psu.sweng500.emrms.util.Constants;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Methods for synching the session with an application.
 */
@Component
public class ApplicationSessionHelper {

	/** The log. */
	private Logger log = LoggerFactory.getLogger(getClass());

	/** The session attributes. */
	private Set<String> sessionAttributes;

	/** The bean factory. */
	@Autowired
	public ListableBeanFactory beanFactory;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Gets the session attributes for all application controllers.
	 *
	 * @return the session attribute names
	 */
	public Set<String> getAllSessionAttributes() {
		// Generate the session attributes on demand.
		// We can't do it on instantiation because we have to wait for all the controllers
		// to be autowired first.
		if(sessionAttributes == null) {
			// Grab all the session attributes used by our controllers from their annotations.
			sessionAttributes = new HashSet<String>();
			for(Object controller : beanFactory.getBeansWithAnnotation(ApplicationFormController.class).values()) {
				SessionAttributes controllerAttributes = AnnotationUtils.findAnnotation(controller.getClass(), SessionAttributes.class);
				if(controllerAttributes != null) {
					for(String attribute : controllerAttributes.value()) {
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
        return (String)session.getAttribute(Constants.APP_USER_LOGIN_ID);
    }
    
    /**
     * Gets the logged in user id.
     *
     * @param session the current session
     * @return the logged in user id, or null if not applicable
     */
    public Integer getApplicationUserId(HttpSession session) {
        return (Integer)session.getAttribute(Constants.APP_USER_ID);
    }
    
    /**
     * Gets the logged in user type.
     *
     * @param session the current session
     * @return the logged in user type, or null if not applicable
     */
    public Long getApplicationUserType(HttpSession session) {
        return (Long)session.getAttribute(Constants.APP_USER_TYPE);
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
		for(String attribute : getAllSessionAttributes()) {
			session.removeAttribute(attribute);
		}
		session.removeAttribute(Constants.APP_USER_ID);
		session.removeAttribute(Constants.APP_USER_LOGIN_ID);
		session.removeAttribute(Constants.APP_USER_TYPE);
		session.removeAttribute(Constants.AUTHORIZED_FOR_EMRMS);
	}
	
}
