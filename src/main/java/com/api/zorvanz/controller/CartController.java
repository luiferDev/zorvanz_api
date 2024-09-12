package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cart.*;
import com.api.zorvanz.domain.products.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @PostMapping
    public ResponseEntity <?> createCart (
            @RequestBody @Valid CartRegisterData cartRegisterData, UriComponentsBuilder uriBuilder
    ) {
        
        var cart = cartService.createCart( cartRegisterData );
        
        var uri = uriBuilder.path( "/api/cart/{id}" )
                .buildAndExpand( cart.id() ).toUri();
        return ResponseEntity.created( uri ).body( cart );
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<CartData> getCartById( @PathVariable Long id, CartRegisterData data ) {
        Cart cart = cartRepository.getReferenceById( id );
//        Cart cart1 = new Cart();
//        var products = productRepository.findAllById( cartService.getProductsbyId( data ) );
//        var cartItems = cartService.getCartItem( data, products, cart1 );
//        var totalAmount = cartService.getTotalAmount( cartItems );
//        cart1.setCartItems( cartItems );
//        cart1.setTotalAmount( totalAmount );
        // TODO: get cartItemData
        return ResponseEntity.ok( new CartData( cart.getId(), null, null ) );
    }
}
