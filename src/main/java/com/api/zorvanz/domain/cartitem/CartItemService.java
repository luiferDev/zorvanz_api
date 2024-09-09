package com.api.zorvanz.domain.cartitem;


import com.api.zorvanz.domain.cart.CartRepository;
import com.api.zorvanz.domain.cart.CartService;
import com.api.zorvanz.domain.products.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        
        if ( ! productRepository.existsById( data.productId() )
                || productRepository.findById( data.productId() ).isEmpty() ) {
            throw new ValidationException( "Producto no existente" );
        }
        if ( ! cartRepository.existsById( data.cartId() ) ) {
            var cartItem = new CartItem();
            cartItem.createCart();
            cartItemRepository.save( cartItem );
        }
        if ( cartItemRepository.existsByProductIdAndCartId( data.productId(), data.cartId() ) )
            throw new ValidationException( "El producto ya esta en el carrito" );
        
        BigDecimal totalPrice = data.unitPrice().multiply(BigDecimal.valueOf(data.quantity()));
        
        var product = productRepository.findById( data.productId() ).get();
        
        var cart = cartRepository.findById( data.cartId() ).get();
        
        var unitPrice =  data.unitPrice();
        
        var cartItem = new CartItem( null, data.quantity(), unitPrice, totalPrice, product, cart );
        
        cartItemRepository.save(cartItem);
        
        return new CartItemData( cartItem );
    }
}
