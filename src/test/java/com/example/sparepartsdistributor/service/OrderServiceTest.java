package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.OrderCreateRequestDto;
import com.example.sparepartsdistributor.entity.Order;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.exception.UserNotFoundException;
import com.example.sparepartsdistributor.repository.OrderRepository;
import com.example.sparepartsdistributor.repository.UserRepository;
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

        var orderRequest = new OrderCreateRequestDto(shippingAddress, userId);

        var user = User.builder()
                .id(userId)
                .build();

        var savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setShippingAddress(shippingAddress);
        savedOrder.setUser(user);

        given(userRepository.findById(orderRequest.userId()))
                .willReturn(Optional.of(user));
        given(orderRepository.save(any(Order.class)))
                .willReturn(savedOrder);
        // when
        var result = underTest.save(orderRequest);
        // then
        assertThat(result.id()).isEqualTo(savedOrder.getId());
        assertThat(result.shippingAddress()).isEqualTo(savedOrder.getShippingAddress());

        var orderArgumentCaptor =
                ArgumentCaptor.forClass(Order.class);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        var capturedOrder = orderArgumentCaptor.getValue();

        assertThat(shippingAddress).isEqualTo(capturedOrder.getShippingAddress());
        assertThat(user).isEqualTo(capturedOrder.getUser());
    }

    @Test
    void willThrowWhenAddOrderUserIsNotFound(){
        Long invalidUserId = 999L;
        var orderRequest = new OrderCreateRequestDto("Kyiv", invalidUserId);
        given(userRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.save(orderRequest))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found with id - " + invalidUserId);
    }
}