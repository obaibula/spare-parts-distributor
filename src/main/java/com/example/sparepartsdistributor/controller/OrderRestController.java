package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.dto.OrderCreateRequestDto;
import com.example.sparepartsdistributor.dto.OrderDto;
import com.example.sparepartsdistributor.service.OrderService;
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
    private ResponseEntity<OrderDto> createUser(@RequestBody OrderCreateRequestDto orderRequest){
        var savedOrder = orderService.save(orderRequest);

        return created(getLocation(savedOrder))
                .body(savedOrder);
    }

    private URI getLocation(OrderDto order) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.id())
                .toUri();
    }
}
