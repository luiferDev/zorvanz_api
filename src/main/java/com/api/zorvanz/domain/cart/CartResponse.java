package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;

import java.util.List;

public record CartResponse(
        Long id,
        Double totalAmount,
        List<CartItem> cartItems
) {
    public CartResponse ( Cart cart ) {
        this(
                cart.getId(),
                cart.getTotalAmount(),
                cart.getCartItems()
                        .stream()
                        .map( cartItem -> new CartItem(
                                cartItem.getId(),
                                cartItem.getQuantity(),
                                cartItem.getUnitPrice(),
                                cartItem.getTotalPrice(),
                                cartItem.getProduct(),
                                cartItem.getCart())
                        )
                        .toList()
        );
    }
}
