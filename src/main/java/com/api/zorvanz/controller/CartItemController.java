/*package com.api.zorvanz.controller;

import com.api.zorvanz.domain.cartitem.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/api/cart-item" )
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	public CartItemController ( CartItemService cartItemService ) {
		this.cartItemService = cartItemService;
	}
	
	@PostMapping ( "/create" )
	@Transactional
	public ResponseEntity createCartItem ( @RequestBody @Valid CartItemRegister data
	                                        UriComponentsBuilder uriBuilder ) {
		//var response = cartItemService.addItemToCart ( data );
		//var uri = uriBuilder.path ( "/api/cart-item/{id}" ).buildAndExpand ( response.id () ).toUri ();
		//return ResponseEntity.ok ( response );
//	}
//}
//*/