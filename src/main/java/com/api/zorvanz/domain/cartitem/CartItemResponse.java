package com.api.zorvanz.domain.cartitem;

import jakarta.validation.constraints.NotNull;

public record CartItemResponse(
        @NotNull
        Long productId,
        @NotNull(message = "Quantity is required")
        Integer quantity
) {
}
