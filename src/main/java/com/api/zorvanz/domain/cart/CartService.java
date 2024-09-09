package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    public CartData createCart ( CartRegisterData cartRegisterData ) {
        return null;
    }
}
