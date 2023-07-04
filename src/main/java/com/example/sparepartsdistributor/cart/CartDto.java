package com.example.sparepartsdistributor.cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// todo: creating a static mapper was a bad consideration for testing.
//  Refactor all dtos into separate service Functions
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
