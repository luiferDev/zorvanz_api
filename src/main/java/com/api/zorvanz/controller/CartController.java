package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cart.CartRegisterData;
import com.api.zorvanz.domain.cart.CartResponse;
import com.api.zorvanz.domain.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping ( "/api/cart" )
public class CartController {

    private final CartService cartService;

    public CartController ( CartService cartService ) {
        this.cartService = cartService;
    }

    /**
     * Crea un carrito con los items proporcionados.
     */
    @PostMapping
    @Transactional
    public ResponseEntity < CartResponse > createCart (
            @RequestBody @Valid CartRegisterData cartRegister,
            UriComponentsBuilder uriBuilder ) {
        CartResponse cart = cartService.createCart ( cartRegister );
        var uri = uriBuilder
                .path ( "/api/cart/{id}" )
                .buildAndExpand ( cart.id () )
                .toUri ();
        return ResponseEntity.created ( uri ).body ( cart );
    }

    /**
     * Obtiene un carrito por su ID.
     */
    @GetMapping ( "/{id}" )
    public ResponseEntity < CartResponse > getCartById ( @PathVariable Long id ) {
        CartResponse response = cartService.getCartById ( id );
        if ( response == null ) {
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( response );
    }

    /**
     * Obtiene todos los carritos.
     */
    @GetMapping ( "/get-carts" )
    public ResponseEntity < List < CartResponse > > getCart () {
        List < CartResponse > response = cartService.getCart ();
        if ( response == null || response.isEmpty () ) {
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( response );
    }

    /**
     * Obtiene carritos de un cliente por su ID.
     */
    @GetMapping ( "/customer-cart/{id}" )
    public ResponseEntity < List < CartResponse > > getCustomerCartById ( @PathVariable Long id ) {
        List < CartResponse > response = cartService.getCartByCustomerId ( id );
        if ( response == null || response.isEmpty () ) {
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok ( response );
    }

    /**
     * Agrega un item al carrito existente.
     */
    @PatchMapping ( "/add-item" )
    @Transactional
    public ResponseEntity < CartResponse > addItemToCart (
            @RequestBody @Valid CartRegisterData cartRegister,
            UriComponentsBuilder uriBuilder ) {
        CartResponse cart = cartService.createCart ( cartRegister );
        var uri = uriBuilder
                .path ( "/api/cart/{id}" )
                .buildAndExpand ( cart.id () )
                .toUri ();
        return ResponseEntity.ok ( cart );
    }
}
