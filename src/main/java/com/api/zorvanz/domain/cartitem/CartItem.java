package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.products.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CartItem")
@Table (name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Double unitPrice;
    private Double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
//    public void getUnitPrice () {
//        this.unitPrice = this.product.getPrice();
//    }
    
//    public void getTotalPrice () {
//        this.totalPrice = this.unitPrice * this.quantity;
//    }
//
}
