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

/**
 * REST controller class that handles requests related to order items.
 */
@RestController
@RequestMapping("/api/v1/orderItems")
@RequiredArgsConstructor
public class OrderItemRestController {
    private final OrderItemService orderItemService;

    /**
     * Creates a new order item based on the provided request body.
     * The created order item is returned in the response with the appropriate HTTP status and location header.
     *
     * @param orderItem the order item to be created, as specified in the request body
     * @return the ResponseEntity containing the created order item and location URI
     */
    @PostMapping
    private ResponseEntity<OrderItem> createOrderItem(
            @RequestBody OrderItem orderItem){
        var savedOrderItem = orderItemService.save(orderItem);

        return ResponseEntity.created(getLocation(savedOrderItem))
                .body(savedOrderItem);
    }

    /**
     * Generates the location URI for the specified saved order item.
     * The location URI is based on the current request URI and the ID of the saved order item.
     *
     * @param orderItem the order item for which the location URI is generated
     * @return the generated location URI for the saved order item
     */
    private URI getLocation(OrderItem orderItem) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderItem.getId())
                .toUri();
    }
}
