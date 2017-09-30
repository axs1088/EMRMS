package edu.psu.sweng500.emrms.controllers.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicate this class is a controller for an application form.  Its session attributes will be
 * cleared when a new user is logged in.
 */
@Target(value={TYPE})
@Retention(value=RUNTIME)
public @interface ApplicationFormController {
}