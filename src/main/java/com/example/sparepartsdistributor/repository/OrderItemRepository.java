package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing order items.
 * Provided CRUD operations and query methods for the {@link OrderItem} entity.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
