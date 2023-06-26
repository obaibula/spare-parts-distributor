package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.CartDto;
import com.example.sparepartsdistributor.dto.CartItemDto;
import com.example.sparepartsdistributor.dto.CartItemToDtoMapper;
import com.example.sparepartsdistributor.dto.PartDto;
import com.example.sparepartsdistributor.entity.Cart;
import com.example.sparepartsdistributor.entity.CartItem;
import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartItemServiceImplTest {
    @Mock private CartItemRepository cartItemRepository;
    @Mock private CartItemToDtoMapper cartItemToDtoMapper;
    private CartItemService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CartItemServiceImpl(cartItemRepository, cartItemToDtoMapper);
    }

    //todo: the test is broken - refactor
    @Test
    void shouldIncreaseQuantityIfPartIsPresentInTheCart(){
        // given
        var cartItem = new CartItem();
        cartItem.setQuantity(5);

        var existingCartItem = new CartItem();
        existingCartItem.setQuantity(8);

        Part part = new Part();
        part.setId(1L);
        Cart cart = new Cart();
        cart.setId(1L);
        cartItem.setPart(part);
        cartItem.setCart(cart);

        given(cartItemRepository.findByUserIdAndPartId(anyLong(), anyLong()))
                .willReturn(Optional.of(existingCartItem));

        given(cartItemRepository.save(any(CartItem.class)))
                .willReturn(existingCartItem);

        given(cartItemToDtoMapper.apply(any(CartItem.class)))
                .willAnswer(answer -> answer.)

        // when
        var result = underTest.save(cartItem);
        // then

        assertThat(result.quantity()).isEqualTo(13);
    }
}