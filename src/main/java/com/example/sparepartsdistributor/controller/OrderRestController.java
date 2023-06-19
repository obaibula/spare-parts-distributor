package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.service.OrderService;
import com.example.sparepartsdistributor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<Order> createUser(@RequestBody Order order){
        Order addedOrder = orderService.save(order);

        return created(getLocation(addedOrder))
                .body(addedOrder);
    }

    private URI getLocation(Order order) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
    }
}
