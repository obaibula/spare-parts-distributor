package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.dto.CartItemDto;
import com.example.sparepartsdistributor.entity.CartItem;
import com.example.sparepartsdistributor.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    @Override
    @Transactional
    public CartItem save(CartItem cartItem) {
        // todo: cart must not contain not unique parts. If part is present in the cart, the quantity must be updated
        /*var partId = cartItem.getPart().getId();
        var cartId = cartItem.getCart().getId();*/
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> findAll() {
        return cartItemRepository.findAllDtos();
    }
}
