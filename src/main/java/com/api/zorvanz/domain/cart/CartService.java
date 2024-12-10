package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.*;
import com.api.zorvanz.domain.customer.Customer;
import com.api.zorvanz.domain.products.Product;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class CartService implements ICartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;

	private CartService ( CartRepository cartRepository,
	                      CartItemRepository cartItemRepository,
						  ProductRepository productRepository){
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
	}

	@Override
	public CartResponse createCart(CartRegisterData cartData) {
		// get customer id
		Long customerId = cartData.customerId();
		if (customerId == null) {
			throw new IllegalArgumentException("Customer ID cannot be null");
		}
		// get list cart Items
		List<CartItemRegister> itemRegisters = cartData.cartItems();
		if (itemRegisters == null || itemRegisters.isEmpty()) {
			throw new IllegalArgumentException("Cart must contain at least one item");
		}
		// get customer
		Customer customer = cartItemRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
		// extract cart item for the products quantity unit price total price
		List<CartItem> cartItems = itemRegisters.stream().map(item -> {
			// Obtener el producto
			Product product = productRepository.findById(item.productId())
					.orElseThrow(() -> new IllegalArgumentException("Product not found"));

			// Validar cantidad
			int quantity = item.quantity();
			if (quantity <= 0) {
				throw new IllegalArgumentException("Invalid quantity");
			}

			// Calcular precio total
			BigDecimal productUnitPrice = product.getPrice();
			BigDecimal totalPrice = productUnitPrice.multiply(BigDecimal.valueOf(quantity))
					.setScale(2, RoundingMode.HALF_UP);

			return new CartItem(null, quantity, product, customer, productUnitPrice, totalPrice);
		}).toList();

		// total amount
		BigDecimal totalAmount = calculateTotalAmount(cartItems);

		// create cart
		Cart cart = new Cart(null, totalAmount, customer, cartItems );
		cartRepository.save(cart);

		// Asociar el carrito con los items y guardar los items
		cartItems.forEach(item -> item.setCart(cart));
		cartItemRepository.saveAll(cartItems);

		return new CartResponse(cart);
	}


	@Override
	public BigDecimal calculateTotalAmount ( List <CartItem> cartItems ) {
		return cartItems.stream ()
				.map ( CartItem::getTotalPrice )
				.filter ( Objects::nonNull )
				.reduce ( BigDecimal.ZERO, BigDecimal::add )
				.setScale ( 2, RoundingMode.HALF_UP );
	}

	@Override
	public CartResponse getCartById ( Long id ) {
		return cartRepository.findById ( id )
				.map ( CartResponse::new )
				.orElseThrow ( () -> new IllegalArgumentException ( "Cart not found" ) );
	}

	@Override
	public List<CartResponse> getCart () {
		return cartRepository.findAll ().stream ().map ( CartResponse::new ).toList ();
	}

	@Override
	public CartResponse getCartByCustomerId( Long customerId) {
		return cartRepository.findByCustomerId(customerId)
				.map(CartResponse::new)
				.orElseThrow(() -> new IllegalArgumentException("Cart not found"));
	}

}

