package com.example.sparepartsdistributor.order;

import com.example.sparepartsdistributor.user.User;
import com.example.sparepartsdistributor.exception.UserNotFoundException;
import com.example.sparepartsdistributor.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link OrderService} interface that provides functionality for managing orders.
 * This class uses the {@link OrderRepository} to interact with the underlying data store
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public OrderDto save(Order order) {
        Long userId = order.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id - " + userId));

        var savedOrder = orderRepository.save(order);

        return OrderDto.orderToDto(savedOrder);
    }
}
