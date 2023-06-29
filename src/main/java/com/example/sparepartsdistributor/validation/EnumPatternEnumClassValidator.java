package com.example.sparepartsdistributor.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class EnumPatternEnumClassValidator implements ConstraintValidator<EnumPattern, Enum<?>> {
    private Pattern pattern;
    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        var matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }
}
