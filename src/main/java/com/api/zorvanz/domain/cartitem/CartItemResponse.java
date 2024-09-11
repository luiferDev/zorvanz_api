package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.products.ProductResponse;

import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        ProductResponse product,
        Long cartId,
        Integer quantity,
        BigDecimal totalPrice,
        BigDecimal unitPrice
) {
}
