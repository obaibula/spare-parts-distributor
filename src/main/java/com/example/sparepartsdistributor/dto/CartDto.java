package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.Cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartDto(Long id,
                      LocalDateTime createdAt,
                      BigDecimal totalPrice) {

    public static CartDto cartToDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getCreatedAt(),
                cart.getTotalPrice());
    }
}
