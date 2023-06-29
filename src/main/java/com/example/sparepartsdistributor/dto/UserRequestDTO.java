package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.ShipmentStatus;
import com.example.sparepartsdistributor.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserRequestDTO(@Email(message = "Invalid email")
                             @UniqueEmail
                             @NotNull
                             String email,
                             @Pattern(regexp = "^\\+38 \\d{3} \\d{3}-\\d{2}-\\d{2}$",
                                     message = "The phone number should be in the next format: +38 050 123-45-67")
                             String phone,
                             @Size(min = 1, max = 30,
                                     message = "Invalid first name: Must be of 1 - 30 characters")
                             String firstName,
                             @Size(min = 1, max = 30,
                                     message = "Invalid last name: Must be of 1 - 30 characters")
                             String lastName,

                             @NotNull(message = "Invalid password: password is NULL")
                             @Pattern(regexp = "^(?=.*\\d)(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).*$",
                                     message = "The password should contain: numbers, " +
                                             "lowercase/uppercase letters, " +
                                             "and special symbols")
                             @Size(min = 8, max = 32, message = "Invalid password: Must be of 8-32 characters")
                             String password,
                             @Min(value = 0,
                                     message = "Balance for new User must be more than 0")
                             BigDecimal balance,
                             @Min(value = 0,
                                     message = "Discount must not be less than 0%")
                             @Max(value = 99,
                                     message = "Discount must not be more than 99%")
                             Integer discount,
                             @Min(value = 0,
                                     message = "Payment deferment must not be less than 0 days")
                             Integer paymentDeferment,
                             @Min(value = 0,
                                     message = "Credit limit must not be less than 0")
                             BigDecimal creditLimit,

                             // todo: correct enum validation
                             /*@EnumPattern(regexp = "^ALLOWED$|^BLOCKED$",
                                     message = "Invalid Shipment status, must be ALLOWED or BLOCKED",
                                     enumClass = ShipmentStatus.class)*/
                             ShipmentStatus shipmentStatus) {
}
