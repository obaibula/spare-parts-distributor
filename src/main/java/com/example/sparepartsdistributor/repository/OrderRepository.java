package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
