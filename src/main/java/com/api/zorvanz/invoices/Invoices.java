package com.api.zorvanz.invoices;

import com.api.zorvanz.domain.orders.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity (name = "Invoices")
@Table (name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Invoices {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Orders orderId;
    private Long customerId;
    private String paymentMethod;
    private LocalDateTime date;
    private BigDecimal amount;
}
