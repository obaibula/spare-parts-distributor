package com.example.sparepartsdistributor.cartitem;

import com.example.sparepartsdistributor.cart.CartDto;
import com.example.sparepartsdistributor.part.PartDto;

public record CartItemDto(
        Long id,
        Integer quantity,
        CartDto cartDto,
        PartDto partDto) {
}
