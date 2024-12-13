package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.cart.CartData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderData(
        Long id,
        Long customerId,
        LocalDateTime date,
        BigDecimal totalAmount,
        Status status,
        Payment paymentMethod,
        CartData cart
) {
    public OrderData(Orders order) {
        this(
                order.getId(),
                order.getCustomer().getId(),
                order.getDate(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getPaymentMethod(),
                new CartData(order.getCart())
        );
    }
}
