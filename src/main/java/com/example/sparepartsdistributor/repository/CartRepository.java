package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
