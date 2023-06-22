package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
