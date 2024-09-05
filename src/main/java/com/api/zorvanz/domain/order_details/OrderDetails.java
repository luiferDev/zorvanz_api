package com.api.zorvanz.domain.order_details;

import com.api.zorvanz.domain.orders.Orders;
import com.api.zorvanz.domain.products.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity (name = "OrderDetails")
@Table (name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;
    private Integer quantity;
    private Double unitPrice;
    private Double totalAmount;
    
    public void setTotalAmount ( ) {
        this.totalAmount = this.unitPrice * this.quantity;
    }
    
    public void setOrder ( Orders order ) {
        this.orderId = order;
    }
    
    public void setProduct ( Product product ) {
        this.productId = product;
    }
}
