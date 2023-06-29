package com.example.sparepartsdistributor.validation;

import com.example.sparepartsdistributor.entity.ShipmentStatus;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumPatternEnumClassValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EnumPattern {
    String regexp();
    String message() default "Invalid Enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass() default ShipmentStatus.class;
}
