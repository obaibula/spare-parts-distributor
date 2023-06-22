package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Cart;
import com.example.sparepartsdistributor.entity.User;

public interface CartService {
    Cart createCart(User user);
}
