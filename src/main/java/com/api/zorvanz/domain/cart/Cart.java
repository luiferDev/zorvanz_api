package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity ( name = "Carts" )
@Table ( name = "carts" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class Cart {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    @OneToMany ( mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    private List <CartItem> cartItems = new ArrayList <>();

//        public void addItem( CartItem cartItem, int quantity) {
//            for ( CartItem item : cartItems ) {
//                if ( item.getProduct().getId().equals( cartItem.getId() ) ) {
//                    item.setQuantity( item.getQuantity() );
//                    return;
//                }
//            }
//
//            var productEnt = new Product( );
//            var unitPrice = productEnt.getPrice();
//            var totalPrice = unitPrice * quantity;
//
//            cartItems.add( new CartItem( null, quantity, totalPrice, unitPrice, productEnt, this) );
//        }
//
//        public void removeItem(Long productId) {
//            cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
//        }
    
    public void setCartItems ( List <CartItem> cartItems ) {
        this.cartItems = cartItems;
        cartItems.forEach( item -> item.setCart( this ) );  // Asegura que cada CartItem tenga la referencia al Cart
    }
    
    public void clearCart () {
        cartItems.clear();
    }
}

