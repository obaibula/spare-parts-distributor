package com.example.sparepartsdistributor.cartitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemToDtoMapper cartItemToDtoMapper;
    @Override
    @Transactional
    public CartItemDto save(CartItem cartItem) {
        var partId = cartItem.getPart().getId();
        var cartId = cartItem.getCart().getId();

        Optional<CartItem> byUserIdAndPartId =
                cartItemRepository.findByCartIdAndPartId(cartId, partId);

        // if cartItem exists, update quantity. Else save this cartItem:
        if(byUserIdAndPartId.isPresent()){
            var persistedCartItem = byUserIdAndPartId.get();
            var currentQuantity = persistedCartItem.getQuantity();
            var quantityFromRequestedItem = cartItem.getQuantity();

            persistedCartItem.setQuantity(currentQuantity + quantityFromRequestedItem);
            CartItemDto resultDto = cartItemToDtoMapper.apply(persistedCartItem);
            return resultDto;

        }else {
            CartItem saved = cartItemRepository.save(cartItem);
            return cartItemToDtoMapper.apply(saved);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> findAll() {
        return cartItemRepository.findAllDtos();
    }
}
