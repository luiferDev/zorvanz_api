package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity ( name = "Orders" )
@Table ( name = "orders" )
@Getter
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
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    Status status;
}
