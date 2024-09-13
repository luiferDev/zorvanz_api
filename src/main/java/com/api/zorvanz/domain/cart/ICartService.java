package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.products.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    
    CartResponse createCart ( CartRegisterData cartRegisterData );
    
    BigDecimal getTotalAmount ( List <CartItem> cartItems );
    
    List <Long> getProductsbyId ( CartRegisterData data );
    
    List <CartItem> getCartItem ( CartRegisterData data, List<Product> products, Cart cart );
    
}
