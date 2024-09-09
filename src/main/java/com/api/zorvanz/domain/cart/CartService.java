package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.cartitem.CartItemData;
import com.api.zorvanz.domain.cartitem.CartItemRepository;
import com.api.zorvanz.domain.cartitem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartItemService cartItemService;
    
    public CartData createCart ( CartRegisterData cartRegisterData ) {
        Cart cart = new Cart();
        cart.setTotalAmount( cartRegisterData.totalAmount() );
        
        var cartItems = cartRegisterData.cartItems();
        
        cartItems.forEach( CartItemData::cartId );
        
        CartItem cartItem = new CartItem();
        
        cart.getCartItems().add( cartItem );
        cartRepository.save( cart );
        
        return new CartData( cart );
    }
    
    public CartData getCartById ( Long id ) {
        return new CartData( cartRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Carrito no encontrado" ) ) );
    }
    
    public void deleteCartById ( Long id ) {
        cartRepository.deleteById( id );
    }
    
    public void deleteAllCartItems ( Long id ) {
        cartItemRepository.deleteAllByCartId( id );
    }
    
    public void deleteCartItemById ( Long id ) {
        cartItemRepository.deleteById( id );
    }
}
