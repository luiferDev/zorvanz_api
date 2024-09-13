package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.cartitem.CartItemRegister;
import com.api.zorvanz.domain.cartitem.CartItemRepository;
import com.api.zorvanz.domain.products.Product;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    private CartService () {
    }
    
    public static CartService createCartService () {
        return new CartService();
    }
    
    @Override
    public CartResponse createCart ( CartRegisterData cartRegisterData ) {
        
        // Obtener los productos usando productIds
        var products = productRepository.findAllById( getProductsbyId( cartRegisterData ) );
        
        // inicializar el Cart
        Cart cart = new Cart( null, BigDecimal.ZERO, new ArrayList <>() );
        
        // Crear CartItems usando productos y cantidades
        var cartItems = getCartItem( cartRegisterData, products, cart );
        
        var totalAmount = getTotalAmount( cartItems );
        
        // Asignar los CartItems al Cart
        cart.setCartItems( cartItems );
        cart.setTotalAmount( totalAmount );
        
        // Guardar CartItems y Cart
        cartRepository.save( cart );
        
        return new CartResponse( cart );
    }
    
    @Override
    public BigDecimal getTotalAmount ( List <CartItem> cartItems ) {
        return cartItems.stream()
                .map( CartItem::getTotalPrice )
                .reduce( BigDecimal.ZERO, BigDecimal::add );
    }
    
    @Override
    public List <Long> getProductsbyId ( CartRegisterData data ) {
        // Obtener los productIds y las cantidades
        List <Long> productIds = data.cartItems().stream()
                .map( CartItemRegister::productId )
                .toList();
        return productIds;
    }
    
    @Override
    public List <CartItem> getCartItem ( CartRegisterData data, List<Product> products, Cart cart ) {
        // obtener los item de los productos
        return new ArrayList <>( data.cartItems().stream()
                .map( cartItemRegister -> {
                    Product product = products.stream()
                            .filter( p -> p.getId().equals( cartItemRegister.productId() ) )
                            .findFirst()
                            .orElseThrow( () -> new IllegalArgumentException( "Product not found" ) );
                    
                    // Convertir quantity a BigDecimal
                    BigDecimal quantity = BigDecimal.valueOf( cartItemRegister.quantity() );
                    
                    // Multiplicar price (BigDecimal) por quantity (BigDecimal)
                    BigDecimal totalPrice = product.getPrice().multiply( quantity );
                    
                    return new CartItem(
                            null,
                            cartItemRegister.quantity(),       // Cantidad original
                            product.getPrice(),                // Precio unitario como BigDecimal
                            totalPrice,                        // Total calculado como BigDecimal
                            product,                           // El producto
                            cart                               // Cart se asignará después
                    );
                } )
                .toList() );
    }
}

