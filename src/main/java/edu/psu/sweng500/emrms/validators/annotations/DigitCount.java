package edu.psu.sweng500.emrms.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.psu.sweng500.emrms.validators.DigitCountValidator;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DigitCountValidator.class)
public @interface DigitCount {
  int value();

  String message() default "{DigitCount}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}