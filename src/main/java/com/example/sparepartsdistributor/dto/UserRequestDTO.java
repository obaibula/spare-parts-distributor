package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.ShipmentStatus;
import com.example.sparepartsdistributor.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserRequestDTO(@Email(message = "Invalid email")
                             @UniqueEmail
                             String email,
                             String phone,
                             String firstName,
                             String lastName,
                             String password,
                             BigDecimal balance,
                             Integer discount,
                             Integer paymentDeferment,
                             BigDecimal creditLimit,
                             ShipmentStatus shipmentStatus) {
}
