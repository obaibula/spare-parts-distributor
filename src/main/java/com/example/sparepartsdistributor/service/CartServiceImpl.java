package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Cart;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    @Override
    public Cart createCart(User user) {
        var cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
