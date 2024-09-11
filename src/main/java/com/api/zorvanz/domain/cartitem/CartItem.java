package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.products.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity ( name = "CartItem" )
@Table ( name = "cart_items" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class CartItem {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    
    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn ( name = "product_id" )
    private Product product;
    
    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn ( name = "cart_id" )
    private Cart cart;
}
