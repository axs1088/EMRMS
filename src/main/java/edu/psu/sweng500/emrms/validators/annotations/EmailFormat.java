package edu.psu.sweng500.emrms.validators.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = EmailFormat.PATTERN, message = "{EmailFormat}")
@ReportAsSingleViolation
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface EmailFormat {

  String message() default "{EmailFormat}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  static final String PATTERN =
          "(" + // Group of the entire pattern
            "([^@ \"\\\\.]|\\.(?!\\.))+" + // Before the @: at least one character. No '"', '\', or '..'
            "@" + // @ separator
            "(" + // Group of subdomains
              "[^@ \"\\\\.]+\\." + // Each subdomain separated by a '.'. at least one character. No '@', ' ', '"', or '\'
            ")+" + // One or more subdomains
            "[^@ \"\\\\.]{2,}" + // Top level domain: at least 2 characters. No '@', ' ', '"', or '\'
          ")?"; // Make the entire pattern optional
}
