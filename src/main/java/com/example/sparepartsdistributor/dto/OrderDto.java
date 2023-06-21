package com.example.sparepartsdistributor.dto;

import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Represents a data transfer object (DTO) for an order.
 */
public record OrderDto(Long id,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt,
                       BigDecimal total,
                       OrderStatus status,
                       String shippingAddress,
                       Long userId) {

    /**
     * Maps an Order entity to an OrderDto.
     *
     * @param order the Order entity to be mapped
     * @return the corresponding OrderDto
     */
    public static OrderDto orderToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getTotal(),
                order.getStatus(),
                order.getShippingAddress(),
                order.getUser().getId());
    }
}
