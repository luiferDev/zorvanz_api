package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemResponse;

import java.util.List;

public record CartRegisterData(
        List <CartItemResponse> cartItems
) {
}
