package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemRegister;

import java.util.List;

public record CartRegisterData(
		Long customerId,
		List <CartItemRegister> cartItems
) {
}
