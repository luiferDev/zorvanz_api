package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.customer.Customer;
import com.api.zorvanz.domain.products.Product;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
/*
@Service
public class CartItemService {
	// el cart item debe recibir un tipo de producto y la cantidad que elija el cliente
	// también debe ser único de un cliente
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	
	public CartItemService ( CartItemRepository cartItemRepository,
	                         ProductRepository productRepository ) {
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
	}
	
	public CartItemData addItemToCart ( CartItemRegister data ) {
		// get customer
		//Customer customer = cartItemRepository.findByCustomerId ( data.customerId () )
		//		.orElseThrow (() -> new IllegalArgumentException ("Customer not found"));
		// get product
		Product product = productRepository.findById ( data.productId () )
				.orElseThrow (() -> new IllegalArgumentException ( "Product not found" ));
		// get quantity
		int quantity = data.quantity ();
		if ( quantity <= 0 ) {
			throw new IllegalArgumentException ( "Invalid quantity" );
		}
		//obtener el precio de cada producto
		BigDecimal productUnitPrice = product.getPrice ();
		// calcular el total
		BigDecimal cartItemTotalPrice = calculateCartItemTotal ( productUnitPrice, quantity );
		// regresa una lista de un Map de productos y la cantidad
		//CartItem cartItem = new CartItem ( null, quantity, product,
		//		customer, productUnitPrice, cartItemTotalPrice );
		/*
		cartItemRepository.save ( cartItem );
		
		Long id = cartItem.getId ();
		int quantityData = cartItem.getQuantity ();
		Long productId = cartItem.getProduct ().getId ();
		Long customerId = cartItem.getCustomer ().getId ();
		BigDecimal unitPriceData = cartItem.getUnitPrice ();
		BigDecimal totalPriceData = cartItem.getTotalPrice ();
		
		return new CartItemData ( id, customerId, productId,
				quantityData, unitPriceData, totalPriceData );
	}
	
	public BigDecimal calculateCartItemTotal ( BigDecimal productUnitPrice, int quantity ) {
		validateInputs ( productUnitPrice, quantity );
		
		return productUnitPrice.multiply ( BigDecimal.valueOf ( quantity ) )
				.setScale ( 2, RoundingMode.HALF_UP );
	}
	
	private void validateInputs ( BigDecimal price, int quantity ) {
		if ( price == null ) {
			throw new IllegalArgumentException ( "Product unit price cannot be null" );
		}
		if ( price.compareTo ( BigDecimal.ZERO ) <= 0 ) {
			throw new IllegalArgumentException ( "Product unit price must be greater than zero" );
		}
		if ( quantity <= 0 ) {
			throw new IllegalArgumentException ( "Quantity must be greater than zero" );
		}
	}
}
*/