package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemResponse;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        Long id,
        BigDecimal totalAmount,
        List <CartItemResponse> cartItems
) {
    public CartResponse ( Cart cart ) {
        this(
                cart.getId(),
                cart.getTotalAmount(),
                cart.getCartItems()
                        .stream()
                        .map( cartItem -> new CartItemResponse(
                                cartItem.getProduct().getId(),
                                cartItem.getQuantity() )
                        )
                        .toList()
        );
    }
}
