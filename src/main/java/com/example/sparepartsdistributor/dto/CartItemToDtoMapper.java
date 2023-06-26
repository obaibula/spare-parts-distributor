package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.CartItem;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

@Service
public class CartItemToDtoMapper implements Function<CartItem, CartItemDto> {
    @Override
    public CartItemDto apply(CartItem cartItem) {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getQuantity(),
                CartDto.cartToDto(cartItem.getCart()),
                PartDto.partToDto(cartItem.getPart()));
    }
}

