package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemData;

import java.math.BigDecimal;
import java.util.List;

public record CartData(
        Long id,
        BigDecimal totalAmount,
        List <CartItemData> cartItems
) {
}

