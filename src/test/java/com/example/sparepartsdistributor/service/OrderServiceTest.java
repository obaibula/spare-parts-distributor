package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.OrderDto;
import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.exception.UserNotFoundException;
import com.example.sparepartsdistributor.repository.OrderRepository;
import com.example.sparepartsdistributor.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock private OrderRepository orderRepository;
    @Mock private UserRepository userRepository;
    private OrderService underTest;

    @BeforeEach
    void setUp() {
        underTest = new OrderServiceImpl(orderRepository, userRepository);
    }

    @Test
    void canAddOrder(){
        // given
        long userId = 1L;
        String shippingAddress = "Kyiv";

        Order order = createOrder(shippingAddress);
        var user = createUser(userId);
        order.setUser(user);

        Order savedOrder = createOrderWithId(shippingAddress);
        savedOrder.setUser(user);

        given(userRepository.findById(order.getUser()
                .getId()))
                .willReturn(Optional.of(user));
        given(orderRepository.save(any(Order.class)))
                .willReturn(savedOrder);
        // when
        var result = underTest.save(order);
        // then
        assertThat(result.id()).isEqualTo(savedOrder.getId());
        assertThat(result.shippingAddress()).isEqualTo(savedOrder.getShippingAddress());
        assertThat(result).isEqualTo(OrderDto.orderToDto(savedOrder));

        var orderArgumentCaptor =
                ArgumentCaptor.forClass(Order.class);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        var capturedOrder = orderArgumentCaptor.getValue();

        assertThat(shippingAddress).isEqualTo(capturedOrder.getShippingAddress());
        assertThat(user).isEqualTo(capturedOrder.getUser());
    }

    @NotNull
    private static Order createOrderWithId(String shippingAddress) {
        var savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setShippingAddress(shippingAddress);
        return savedOrder;
    }

    private static User createUser(long userId) {
        return User.builder()
                .id(userId)
                .build();
    }

    @NotNull
    private static Order createOrder(String shippingAddress) {
        var order = new Order();
        order.setShippingAddress(shippingAddress);
        return order;
    }

    @Test
    void willThrowWhenAddOrderUserIsNotFound(){
        long invalidUserId = 999L;
        var order = new Order();
        order.setUser(createUser(invalidUserId));

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.save(order))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found with id - " + invalidUserId);
    }
}