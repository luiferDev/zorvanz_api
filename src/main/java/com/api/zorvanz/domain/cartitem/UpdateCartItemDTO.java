package com.api.zorvanz.domain.cartitem;

import jakarta.validation.constraints.NotNull;

public record UpdateCartItemDTO(
		Long productId,
		Integer quantity,
		Long customerId,
		Long cartId
) {
}
