package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.entity.CartItem;
import com.example.sparepartsdistributor.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/cartItems")
@RequiredArgsConstructor
public class CartItemRestController {
    private final CartItemService cartItemService;

    @PostMapping
    private ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem){
        var saved = cartItemService.save(cartItem);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cartItem.getId())
                .toUri())
                .body(saved);
    }
}
