package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.products.Product;

public record CartItemResponse(
        Long id,
        Cart cartId,
        Product productId,
        Integer quantity,
        Double totalPrice,
        Double unitPrice
) {
}
