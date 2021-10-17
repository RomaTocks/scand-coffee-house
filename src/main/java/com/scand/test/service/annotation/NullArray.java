package com.scand.test.service.annotation;

import com.scand.test.service.annotation.implementation.NullArrayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, отвечающая за проверку элементов на null.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullArrayValidator.class)
public @interface NullArray
{
    String message() default "Null-array detected!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
