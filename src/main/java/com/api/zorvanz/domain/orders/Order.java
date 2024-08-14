package com.api.zorvanz.domain.orders;

import java.util.Date;

public record Order(
        Long id,
        Long customerId,
        Date orderDate,
        Double totalAmount,
        String status
) {
}
