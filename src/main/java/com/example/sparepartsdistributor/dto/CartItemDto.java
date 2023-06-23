package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.CartItem;

public record CartItemDto(
        Long id,
        Integer quantity,
        CartDto cartDto,
        PartDto partDto) {

    public static CartItemDto cartItemToDto(CartItem cartItem) {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getQuantity(),
                CartDto.cartToDto(cartItem.getCart()),
                PartDto.partToDto(cartItem.getPart()));
    }
}
