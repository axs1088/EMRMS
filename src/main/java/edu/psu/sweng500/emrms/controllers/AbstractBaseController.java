package com.pennmutual.preference.controllers;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.pennmutual.preference.application.ApplicationSecurityInterceptor;
import com.pennmutual.preference.validators.PreferenceBindingErrorProcessor;

/**
 * Base controller for all application forms.
 */
public abstract class AbstractBaseController<T> {

    public static final Pattern KEY_WITH_MISSING_QUOTES = Pattern.compile("\\[([^\"]+)\\]");

    /** Whether the user is authorized for Preference UI */
    public static final String AUTHORIZED_FOR_PREFERENCE = "AuthorizedForPreference";

    /** Template attribute for the current FieldInfo instance. */
    public static final String MODEL_FIELD_INFO = "fieldInfo";

    /** Template attribute for the current application status. */
    public static final String MODEL_FIELD_STATUS = "currentStatus";

    /** Template (flash) attribute for a binding result with errors. */
    public static final String MODEL_BINDING_RESULT = "bindingResult";

    /** Template (flash) attribute for a list of error messages. */
    public static final String MODEL_ERRORS = "errors";

    /** Template (flash) attribute for a map of URLs to a list of error messages. */
    public static final String MODEL_ERRORS_BY_URL = "errorsByUrl";

    /** The logger. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /** The FreeMarker configurer. */
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    /** The default validator. */
    @Autowired
    private Validator defaultValidator;

    /**
     * Get the relative URL for this controller, e.g. "start" for Start
     * 
     * @return relative URL string
     */
    public abstract String getRelativeURL();

    /**
     * Gets the paths of required fields for this page.
     * 
     * @param session
     *            the current session
     * @return the required paths
     */
    public abstract Set<String> getRequiredPaths(HttpSession session);

    /**
     * Override this to get the hidden required paths for a controller.
     * 
     * These are paths that can be hidden depending on user input, but should always be required when visible.
     * 
     * @param session
     *            the current session
     * @param type
     *            the application type
     * @return the hidden required paths
     */
    public Set<String> getHiddenRequiredPaths(HttpSession session, String type) {
        return new HashSet<String>();
    }

    /**
     * Run all validation for this controller.
     * 
     * @param session
     *            the session
     * @return the binding result
     */
    public abstract Errors runAllValidation(HttpSession session);

    /**
     * getAuthorizedForPreference
     * 
     * @param session the current session
     * @return authorizedForPreference
     */
    public boolean getAuthorizedForPreference(HttpSession session) {
        Boolean authorizedForPreference = (Boolean) session.getAttribute(ApplicationSecurityInterceptor.AUTHORIZED_FOR_PREFERENCE);
        if (authorizedForPreference == null) {
            authorizedForPreference = false;
        }
        return authorizedForPreference;
    }

    /**
     * Gets the model bound to this controller. If the controller has more than one model, return the main one.
     * 
     * @param session
     *            the current session
     * @return the model
     */
    public abstract T getModel(HttpSession session);

    /**
     * Gets a form object from the current HTTP session.
     * 
     * <p>
     * <b>Deprecated.</b> Use the specific methods inside the controllers instead, e.g. StartController.getForm().
     * 
     * @param attributeName
     *            the session attribute name, e.g. StartController.FORM_NAME
     * @return the stored form
     */
    @Deprecated
    public static Object getSessionForm(String attributeName) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);
        return session.getAttribute(attributeName);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new PreferenceBindingErrorProcessor());
    }

    /**
     * Gets all the fields for model that have the NotNull annotation.
     * 
     * @param model
     *            the model
     * @param prefix
     *            an optional prefix to add before each field name
     * @return the not null fields for model
     */
    protected static Set<String> getNotNullFieldsForModel(Class<?> model, String prefix) {
        Set<String> fields = new HashSet<String>();
        for (Field field : model.getDeclaredFields()) {
            if ((AnnotationUtils.getAnnotation(field, NotNull.class) != null) || (AnnotationUtils.getAnnotation(field, NotBlank.class) != null)
                    || (AnnotationUtils.getAnnotation(field, NotEmpty.class) != null)) {
                String name = field.getName();
                if (prefix != null) {
                    name = prefix + name;
                }
                fields.add(name);
            }
        }
        return fields;
    }

    /**
     * Gets all the fields for model that have the NotNull annotation.
     * 
     * @param model
     *            the model
     * @return the not null fields for model
     */
    protected static Set<String> getNotNullFieldsForModel(Class<?> model) {
        Set<String> fields = new HashSet<String>();
        for (Field field : model.getDeclaredFields()) {
            if (AnnotationUtils.getAnnotation(field, NotNull.class) != null) {
                fields.add(field.getName());
            }
        }
        return fields;
    }

    /**
     * Gets the FreeMarker configurer.
     * 
     * @return the FreeMarker configurer
     */
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    /**
     * Sets the FreeMarker configurer.
     * 
     * @param freeMarkerConfigurer
     *            the new FreeMarker configurer
     */
    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Gets the default validator.
     * 
     * @return the default validator
     */
    public Validator getDefaultValidator() {
        return defaultValidator;
    }

    /**
     * Sets the default validator.
     * 
     * @param defaultValidator
     *            the new default validator
     */
    public void setDefaultValidator(Validator defaultValidator) {
        this.defaultValidator = defaultValidator;
    }

    /**
     * Creates a map to put into an empty select.
     * 
     * @return the map
     */
    protected LinkedHashMap<String, String> newEmptySelectValues() {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        result.put("", "--- Select ---");
        return result;
    }

    /**
     * Check if all the properties annotated @NotNull in a model object are not null.
     * 
     * @param model
     *            the model
     * @return true, if all required fields are present
     */
    protected boolean modelHasAllRequiredFields(Object model) {
        Set<String> requiredFields = getNotNullFieldsForModel(model.getClass());
        if (!requiredFields.isEmpty()) {
            BeanWrapperImpl bean = new BeanWrapperImpl(model);
            for (String fieldName : requiredFields) {
                if (bean.getPropertyValue(fieldName) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Fix map key quotes. Change path[key] to path["key"]
     * 
     * @param path
     *            the fixed path
     * @return the input path
     */
    protected String fixMapKeyQuotes(String path) {
        Matcher matcher = KEY_WITH_MISSING_QUOTES.matcher(path);
        return matcher.replaceAll("[\"$1\"]");
    }
}
