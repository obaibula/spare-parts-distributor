package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.entity.OrderItem;
import com.example.sparepartsdistributor.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orderItems")
@RequiredArgsConstructor
public class OrderItemRestController {
    private final OrderItemService orderItemService;

    @PostMapping
    private ResponseEntity<OrderItem> createOrderItem(
            @RequestBody OrderItem orderItem){
        var savedOrderItem = orderItemService.save(orderItem);

        return ResponseEntity.created(getLocation(savedOrderItem))
                .body(savedOrderItem);
    }

    private URI getLocation(OrderItem savedOrderItem) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderItem.getId())
                .toUri();
    }
}
