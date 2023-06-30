package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.repository.UserRepository;
import com.example.sparepartsdistributor.validation.UniqueEmailValidator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserRequestDTOTest {
    private Validator validator;

    @Mock
    private UserRepository userRepository;

    private UniqueEmailValidator uniqueEmailValidator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        uniqueEmailValidator = new UniqueEmailValidator(userRepository);
    }

    @Test
    void emailShouldNotBeNull(){
        // given

        var requestDTO = UserRequestDTO.builder()
                .email(null)
                .password("321kjkK3#@1")
                .build();

        // when
        var violations = validator.validate(requestDTO);

        // then
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email must not be null");
    }
}
