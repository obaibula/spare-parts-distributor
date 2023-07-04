package com.example.sparepartsdistributor.cartitem;

import com.example.sparepartsdistributor.cart.CartDto;
import com.example.sparepartsdistributor.cartitem.CartItem;
import com.example.sparepartsdistributor.cartitem.CartItemDto;
import com.example.sparepartsdistributor.part.PartDto;
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

