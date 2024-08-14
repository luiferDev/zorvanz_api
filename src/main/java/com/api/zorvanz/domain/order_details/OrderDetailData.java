package com.api.zorvanz.domain.order_details;

public record OrderDetailData(
        Long orderDetailId,
        Long orderId,
        Long productId,
        Integer quantity,
        Double unitPrice
) {
}
