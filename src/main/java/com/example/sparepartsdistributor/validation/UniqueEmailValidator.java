package com.example.sparepartsdistributor.validation;

import com.example.sparepartsdistributor.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraint) {
        return !userRepository.existsByEmail(email);
    }
}

