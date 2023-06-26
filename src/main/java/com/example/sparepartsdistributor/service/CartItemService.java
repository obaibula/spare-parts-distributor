package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.CartItemDto;
import com.example.sparepartsdistributor.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItemDto save(CartItem cartItem);

    List<CartItemDto> findAll();
}
