package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cart.CartRegisterData;
import com.api.zorvanz.domain.cart.CartResponse;
import com.api.zorvanz.domain.cart.CartService;
import com.api.zorvanz.domain.cartitem.CartItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping ( "/api/cart" )
public class CartController {
	private final CartItemRepository cartItemRepository;
	private final CartService cartService;
	
	public CartController ( CartItemRepository cartItemRepository,
	                        CartService cartService ) {
		this.cartItemRepository = cartItemRepository;
		this.cartService = cartService;
	}
	
	// para crear un carrito debo entregarle una lista de cartItem
	// estos tendrán que calcular
	@PostMapping
	@Transactional
	public ResponseEntity<CartResponse> createCart(
			@RequestBody @Valid CartRegisterData cartRegister,
			UriComponentsBuilder uriBuilder
	) {
		var cart = cartService.createCart(cartRegister);
		var uri = uriBuilder.path("/api/cart/{id}")
				.buildAndExpand(cart.id()).toUri();
		return ResponseEntity.created(uri).body(cart);
	}

	@GetMapping ( "/{id}" )
	public ResponseEntity<CartResponse> getCartById ( @PathVariable Long id ) {
		var response = cartService.getCartById(id);
		if ( response == null ) {
			return ResponseEntity.notFound ().build ();
		}

		return ResponseEntity.ok ( response );
	}

	@GetMapping ( "/get-carts" )
	@Async("threadPoolTaskExecutor")
	public CompletableFuture< ResponseEntity<List <CartResponse>> >  getCart () {
		var response = cartService.getCart ();
		if ( response == null ) {
			return CompletableFuture.completedFuture ( ResponseEntity.notFound ().build () );
		}

		return CompletableFuture.completedFuture ( ResponseEntity.ok ( response ) );
	}

	@GetMapping ( "/customer-cart/{id}" )
	public ResponseEntity<CartResponse> getCustomerCartById ( @PathVariable Long id ) {
		var response = cartService.getCartByCustomerId(id);
		if ( response == null ) {
			return ResponseEntity.notFound ().build ();
		}

		return ResponseEntity.ok ( response );
	}
	//TODO: implementar el método para actualizar un carrito
	//@PutMapping ( "/{id}" )
	//TODO: implementar el método para agregar un item al carrito
	//@PostMapping ( "/{id}" )
	//TODO: implementar el método para eliminar un carrito
	//@DeleteMapping ( "/{id}" )
}
