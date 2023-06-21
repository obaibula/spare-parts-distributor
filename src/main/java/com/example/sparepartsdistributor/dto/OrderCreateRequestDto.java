package com.example.sparepartsdistributor.dto;

/**
 * Represents a data transfer object (DTO) for creating an order.
 */
public record OrderCreateRequestDto(String shippingAddress, Long userId) {
}