package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.cartitem.CartItemRepository;
import com.api.zorvanz.domain.cartitem.CartItemResponse;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public CartResponse createCart ( CartRegisterData cartRegisterData ) {
        // get price from product repository
        List <Long> productIds = cartRegisterData.cartItems().stream()
                .map( cartItemResponse1 -> cartItemResponse1.productId().getId() )
                .toList();
        
        var price = productRepository.findPricesByProductIds( productIds );
        
        // get quantity from cartItem
        var quantity = cartRegisterData.cartItems().stream()
                .map( CartItemResponse::quantity )
                .toList();
        // calculate totalAmount
        var totalAmount = calculateTotal( quantity, price );
        // look for the productId and add to the cart
        
        var products = cartItemRepository.findAllById( productIds );
        
        var cartItem = new ArrayList <>( cartRegisterData.cartItems()
                .stream()
                .map(
                        cartItemResponse ->
                                new CartItem(
                                        null,
                                        cartItemResponse.quantity(),
                                        cartItemResponse.unitPrice(),
                                        cartItemResponse.totalPrice(),
                                        cartItemResponse.productId(),
                                        cartItemResponse.cartId() ) )
                .toList() );
        cartItem.addAll( products );
        // save cartItem
        cartItemRepository.saveAll( cartItem );
        // create the cart and initialize with the data
        var cart = new Cart( null, totalAmount, cartItem );
        // add items to the cart item
        // save cart
        cartRepository.save( cart );
        //return cart response
        return new CartResponse( cart );
    }
    
    public double calculateTotal(List<Integer> quantities, List<Double> prices) {
        if ( quantities.size() != prices.size() ) {
            throw new IllegalArgumentException( "Las listas deben tener el mismo tamaño." );
        }
        // Multiplication las listas y suma el total
        
        return IntStream.range( 0, quantities.size() )
                .mapToDouble( i -> quantities.get( i ) * prices.get( i ) )
                .sum();
    }
}

