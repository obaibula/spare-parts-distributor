package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.OrderCreateRequestDto;
import com.example.sparepartsdistributor.dto.OrderDto;
import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.exception.UserNotFoundException;
import com.example.sparepartsdistributor.repository.OrderRepository;
import com.example.sparepartsdistributor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public OrderDto save(OrderCreateRequestDto orderRequest) {
        Long userId = orderRequest.userId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id - " + userId));

        Order order = new Order();
        order.setShippingAddress(orderRequest.shippingAddress());
        order.setUser(user);

        var savedOrder = orderRepository.save(order);

        return OrderDto.orderToDto(savedOrder);
    }
}
