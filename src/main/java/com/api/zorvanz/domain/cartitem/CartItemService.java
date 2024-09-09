package com.api.zorvanz.domain.cartitem;


import com.api.zorvanz.domain.cart.CartRepository;
import com.api.zorvanz.domain.cart.CartService;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartService cartService;
    
    public CartItemData addItemToCart ( CartItemRegister data ) {
        return null;
    }
}
