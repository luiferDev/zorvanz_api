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
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public CartResponse createCart ( CartRegisterData cartRegisterData ) {
        
        // Obtener los productIds y las cantidades
        List <Long> productIds = cartRegisterData.cartItems().stream()
                .map( CartItemRegister::productId )
                .toList();
        
        // Obtener los productos usando productIds
        var products = productRepository.findAllById( productIds );
        
        // Obtener las cantidades
//        var quantities = cartRegisterData.cartItems().stream()
//                .map( CartItemResponse::quantity )
//                .toList();
        
        // Obtener los precios de los productos
        // sumar totalPrice de cada cartId en cartItem
        var prices = products.stream()
                .map( Product::getPrice )
                .toList();
        
        // Calcular el totalAmount
        // BigDecimal totalAmount = calculateTotal( quantities, prices );
        
//        BigDecimal totalAmount = prices.stream()
//                .reduce( BigDecimal.ZERO, BigDecimal::add );
        
        // Crear el Cart
        var cart = new Cart( null, BigDecimal.ZERO, new ArrayList <>() );
        
        // Crear CartItems usando productos y cantidades
        var cartItems = new ArrayList <>( cartRegisterData.cartItems().stream()
                .map( cartItemResponse -> {
                    Product product = products.stream()
                            .filter( p -> p.getId().equals( cartItemResponse.productId() ) )
                            .findFirst()
                            .orElseThrow( () -> new IllegalArgumentException( "Product not found" ) );
                    
                    // Convertir quantity a BigDecimal
                    BigDecimal quantity = BigDecimal.valueOf( cartItemResponse.quantity() );
                    
                    // Multiplicar price (BigDecimal) por quantity (BigDecimal)
                    BigDecimal totalPrice = product.getPrice().multiply( quantity );
                    
                    return new CartItem(
                            null,
                            cartItemResponse.quantity(),       // Cantidad original
                            product.getPrice(),                // Precio unitario como BigDecimal
                            totalPrice,                        // Total calculado como BigDecimal
                            product,                           // El producto
                            cart                               // Cart se asignará después
                    );
                } )
                .toList() );
        
        var totalAmount = cartItems.stream()
                .map( CartItem::getTotalPrice )
                .reduce( BigDecimal.ZERO, BigDecimal::add );
        
        // Asignar los CartItems al Cart
        cart.setCartItems( cartItems );
        cart.setTotalAmount( totalAmount );
        
        // Guardar CartItems y Cart
        cartRepository.save( cart );
        
        return new CartResponse( cart );
    }
    
    public BigDecimal calculateTotal ( List <Integer> quantities, List <BigDecimal> prices ) {
        if ( quantities.size() != prices.size() ) {
            throw new IllegalArgumentException( "Las listas deben tener el mismo tamaño." );
        }
        
        // Inicializar el total como BigDecimal
        BigDecimal total = new BigDecimal( "0.00" );
        
        // Iterar sobre las listas de cantidades y precios
        for ( int i = 0; i < quantities.size(); i++ ) {
            
            BigDecimal price = prices.get( i );   // Obtener cada precio como BigDecimal
            BigDecimal quantity = BigDecimal.valueOf( quantities.get( i ) ); // Convertir cantidad a BigDecimal
            
            // Multiplicar precio por cantidad y sumarlo al total
            var totalAmount = price.multiply( quantity );
            
            total = total.add( totalAmount );
        }
        
        return total;
    }
}

