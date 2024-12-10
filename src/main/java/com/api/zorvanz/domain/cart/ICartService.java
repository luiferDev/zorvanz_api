package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.cartitem.CartItemData;
import com.api.zorvanz.domain.cartitem.CartItemRegister;
import com.api.zorvanz.domain.products.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
	CartResponse createCart ( CartRegisterData cartData );
	
	BigDecimal calculateTotalAmount ( List <CartItem> cartItems );

	CartResponse getCartById ( Long id );

	List<CartResponse> getCart ();

	CartResponse getCartByCustomerId( Long customerId );
}
