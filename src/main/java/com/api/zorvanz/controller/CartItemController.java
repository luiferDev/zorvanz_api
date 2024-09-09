package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cartitem.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartItemService cartItemService;

//    @GetMapping ("/{id}")
//    public ResponseEntity <CartItemData> getCartItemById( @PathVariable Long id ) {
//        CartItem cartItem = cartItemRepository.getReferenceById( id );
//        return ResponseEntity.ok( new CartItemData( cartItem ) );
//    }
    
    @PostMapping
    public ResponseEntity createCartItem ( @RequestBody @Valid CartItemRegister data ) {
        var response = cartItemService.addItemToCart( data );
        return ResponseEntity.ok( response );
    }
}
