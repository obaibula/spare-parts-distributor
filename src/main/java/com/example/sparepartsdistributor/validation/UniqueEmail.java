package com.example.sparepartsdistributor.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface  UniqueEmail {
    String message() default "Email address is already registered";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
