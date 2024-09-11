package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cart.CartRegisterData;
import com.api.zorvanz.domain.cart.CartRepository;
import com.api.zorvanz.domain.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartService cartService;
    
//    @GetMapping("/{id}")
//    public ResponseEntity<CartData> getCartById( @PathVariable Long id ) {
//        Cart cart = cartRepository.getReferenceById( id );
//        return ResponseEntity.ok( new CartData( cart ) );
//    }
    
    @PostMapping
    public ResponseEntity <?> createCart (
            @RequestBody @Valid CartRegisterData cartRegisterData, UriComponentsBuilder uriBuilder
    ) {
        
        var cart = cartService.createCart( cartRegisterData );
        
        var uri = uriBuilder.path( "/api/cart/{id}" )
                .buildAndExpand( cart.id() ).toUri();
        return ResponseEntity.created( uri ).body( cart );
    }
    
}
