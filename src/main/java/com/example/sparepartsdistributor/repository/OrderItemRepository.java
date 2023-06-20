package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
