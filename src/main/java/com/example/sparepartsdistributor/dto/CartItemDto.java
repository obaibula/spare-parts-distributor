package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.CartItem;

public record CartItemDto(
        Long id,
        Integer quantity,
        CartDto cartDto,
        PartDto partDto) {
}
