package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.CartItemToDtoMapper;
import com.example.sparepartsdistributor.entity.Cart;
import com.example.sparepartsdistributor.entity.CartItem;
import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartItemServiceImplTest {
    @Mock private CartItemRepository cartItemRepository;
    private final CartItemToDtoMapper cartItemToDtoMapper = new CartItemToDtoMapper();;
    private CartItemService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CartItemServiceImpl(cartItemRepository, cartItemToDtoMapper);
    }
    @Test
    void shouldIncreaseQuantityIfPartIsPresentInTheCart(){
        // given
        Part part = new Part();
        part.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);

        var cartItem = new CartItem();
        cartItem.setQuantity(5);

        var existingCartItem = new CartItem();
        existingCartItem.setId(1L);
        existingCartItem.setQuantity(8);

        cartItem.setPart(part);
        cartItem.setCart(cart);

        existingCartItem.setPart(part);
        existingCartItem.setCart(cart);

        given(cartItemRepository.findByCartIdAndPartId(1L, 1L))
                .willReturn(Optional.of(existingCartItem));

        // when
        var result = underTest.save(cartItem);
        // then

        assertThat(result.quantity()).isEqualTo(13);
    }

    //todo: refactor the next test and similar tests.

    @Test
    void shouldSaveCartItemIfPartIsNotPresentInTheCart(){
        // given
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(5);

        Part part = new Part();
        part.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);

        cartItem.setPart(part);
        cartItem.setCart(cart);

        given(cartItemRepository.findByCartIdAndPartId(anyLong(), anyLong()))
                .willReturn(Optional.empty());

        ArgumentCaptor<CartItem> captor = ArgumentCaptor.forClass(CartItem.class);
        given(cartItemRepository.save(captor.capture()))
                .willReturn(cartItem);

        // when
        var result = underTest.save(cartItem);

        // then
        assertThat(result.quantity()).isEqualTo(5);
        assertThat(captor.getValue()).isEqualTo(cartItem);
        verify(cartItemRepository).save(any(CartItem.class));
    }
}