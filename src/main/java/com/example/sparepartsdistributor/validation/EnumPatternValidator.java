package com.example.sparepartsdistributor.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, String> {
    private Set<String> enumNames;

    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        var enumClass = constraintAnnotation.enumClass();

        enumNames = getNamesSet(enumClass);
    }

    private Set<String> getNamesSet(Class<? extends Enum<? extends Enum<?>>> enumClass) {
        var enums = enumClass.getEnumConstants();
        var names = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            names[i] = enums[i].toString();
        }
        return new HashSet<>(Arrays.asList(names));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        else
            return enumNames.contains(value);
    }
}
