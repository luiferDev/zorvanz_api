package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.UpdateCartItemDTO;

import java.util.List;

public record UpdateCartDTO(
		Long cartId,
		List < UpdateCartItemDTO > cartItems
) {

}
