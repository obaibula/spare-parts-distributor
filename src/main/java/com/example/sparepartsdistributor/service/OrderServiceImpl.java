package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
