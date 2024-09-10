package com.api.zorvanz.domain.cartitem;

public record CartItemData(
        Long id,
        Long cartId,
        Long productId,
        Integer quantity
) {
}

