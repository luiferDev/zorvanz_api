package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Carts")
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
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
        
        public List<CartItem> getItems() {
            return cartItems;
        }
        
        public void clearCart() {
            cartItems.clear();
        }
}

