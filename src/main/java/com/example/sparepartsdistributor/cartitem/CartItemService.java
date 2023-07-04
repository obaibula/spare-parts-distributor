package com.example.sparepartsdistributor.cartitem;

import java.util.List;

public interface CartItemService {
    CartItemDto save(CartItem cartItem);

    List<CartItemDto> findAll();
}
