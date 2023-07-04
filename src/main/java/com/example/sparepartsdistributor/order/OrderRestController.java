package com.example.sparepartsdistributor.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

/**
 * REST controller class that handles requests related to orders.
 */
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    /**
     * Creates a new order based on the provided request body.
     * The created order is returned in the response with the appropriate HTTP status and location header.
     *
     * @param order the order creation request DTO, as specified in the request body
     * @return the ResponseEntity containing the created order DTO and location URI
     */
    @PostMapping
    private ResponseEntity<OrderDto> createUser(@RequestBody Order order){
        var savedOrder = orderService.save(order);

        return created(getLocation(savedOrder))
                .body(savedOrder);
    }

    /**
     * Generates the location URI for the specified order.
     * The location URI is based on the current request URI and the ID of the order.
     *
     * @param order the orderDto for which the location URI is generated
     * @return the generated location URI for the orderDto
     */
    private URI getLocation(OrderDto order) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.id())
                .toUri();
    }
}
