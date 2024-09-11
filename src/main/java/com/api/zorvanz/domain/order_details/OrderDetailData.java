package com.api.zorvanz.domain.order_details;

import java.math.BigDecimal;

public record OrderDetailData(
        Long orderDetailId,
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalAmount
) {
}
