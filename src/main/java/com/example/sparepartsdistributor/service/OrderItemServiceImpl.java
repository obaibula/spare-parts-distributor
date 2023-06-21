package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.OrderItem;
import com.example.sparepartsdistributor.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link OrderItemService} interface that provides functionality for managing order items
 * This class uses the {@link OrderItemRepository} to interact with the underlying data store
 */
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    /**
     * Saves the provided order item.
     * This method is transactional, ensuring that the save operation is atomic.
     * @param orderItem the order item to be saved
     * @return the saved order item
     */
    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
