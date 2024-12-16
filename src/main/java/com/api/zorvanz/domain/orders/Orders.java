package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity ( name = "Orders" )
@Table ( name = "orders" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime date;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Enumerated( EnumType.STRING )
    private Payment paymentMethod;

}
