package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing orders.
 * Provided CRUD operations and query methods for the {@link Order} entity.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
