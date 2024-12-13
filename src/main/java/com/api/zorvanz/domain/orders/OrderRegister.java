package com.api.zorvanz.domain.orders;

public record OrderRegister(
		Long cartId,
		Long customerId,
		Payment paymentMethod
) {
}
