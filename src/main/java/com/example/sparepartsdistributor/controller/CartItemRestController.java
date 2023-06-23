package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.dto.CartItemDto;
import com.example.sparepartsdistributor.entity.CartItem;
import com.example.sparepartsdistributor.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/cartItems")
@RequiredArgsConstructor
public class CartItemRestController {
    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        var saved = cartItemService.save(cartItem);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(cartItem.getId())
                        .toUri())
                .body(saved);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<CartItemDto>> all() {
        var cartItems = cartItemService.findAll()
                .stream()
                .map(this::getCartItemEntityModel)
                .toList();

        return CollectionModel.of(
                cartItems,
                getLinkToAllMethod().withSelfRel());
    }

    private EntityModel<CartItemDto> getCartItemEntityModel(CartItemDto cartItem) {
        return EntityModel.of(
                cartItem,
                getLinkToAllMethod()
                        .withRel("cartItems"));
    }

    private WebMvcLinkBuilder getLinkToAllMethod() {
        return linkTo(methodOn(CartItemRestController.class).all());
    }
}
