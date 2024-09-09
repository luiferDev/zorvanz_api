package com.api.zorvanz.domain.cartitem;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartItemRegister(
        Long cartId,
        @NotNull(message = "Campo \"productId\" es requerido")
        Long productId,
        @NotNull(message = "Campo \"quantity\" es requerido")
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
}
