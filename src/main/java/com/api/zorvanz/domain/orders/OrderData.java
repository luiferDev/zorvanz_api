package com.api.zorvanz.domain.orders;

import java.util.Date;

public record OrderData(
        Long id,
        Long customerId,
        Date orderDate,
        Double totalAmount,
        Status status
) {
}
