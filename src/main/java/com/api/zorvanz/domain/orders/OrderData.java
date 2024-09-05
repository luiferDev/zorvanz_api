package com.api.zorvanz.domain.orders;

import java.time.LocalDateTime;

public record OrderData(
        Long id,
        Long customerId,
        LocalDateTime date,
        Double totalAmount,
        Status status
) {
    public OrderData(Orders order) {
        this(
                order.getId(),
                order.getCustomer().getId(),
                order.getDate(),
                order.getTotalAmount(),
                order.getStatus()
        );
    }
}
