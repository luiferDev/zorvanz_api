package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemData;

import java.util.List;

public record CartData(
        Long id,
        Double totalAmount,
        List <CartItemData> cartItems
) {
}

