package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orders implements Serializable {  // Add Serializable interface

    @Serial
    private static final long serialVersionUID = 1L;  // Add serialVersionUID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)  // Add nullable constraint
    private Customer customer;

    @Column(name = "total_amount", precision = 10, scale = 2)  // Add precision and scale
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)  // Add nullable constraint
    private Status status;

    @Column(name = "date", nullable = false)  // Add nullable constraint
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)  // Add nullable constraint
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)  // Add nullable constraint
    private Payment paymentMethod;
}
