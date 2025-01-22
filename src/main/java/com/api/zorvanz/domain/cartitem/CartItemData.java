package com.api.zorvanz.domain.cartitem;

import java.math.BigDecimal;

public record CartItemData(
		Long id,
		Long customerId,
		Long cartId,
		Long productId,
		Integer quantity,
		BigDecimal unitPrice,
		BigDecimal totalPrice
) {
	public CartItemData(CartItem cartItem) {
		this(
				cartItem.getId(),
				cartItem.getCustomer().getId(),
				cartItem.getProduct().getId(),
				cartItem.getCart ().getId(),
				cartItem.getQuantity(),
				cartItem.getUnitPrice(),
				cartItem.getTotalPrice()
		);
	}
}

