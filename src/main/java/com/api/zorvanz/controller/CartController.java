package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cart.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/{id}")
    public ResponseEntity<CartData> getCartById( @PathVariable Long id ) {
        Cart cart = cartRepository.getReferenceById( id );
        return ResponseEntity.ok( new CartData( cart ) );
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity createCart ( @RequestBody @Valid CartRegisterData cartRegisterData ) {
        var cart = cartService.createCart( cartRegisterData );
        return ResponseEntity.ok( cart );
    }
}
