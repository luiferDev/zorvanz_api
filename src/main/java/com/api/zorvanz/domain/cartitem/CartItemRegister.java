package com.api.zorvanz.domain.cartitem;

import jakarta.validation.constraints.NotNull;

public record CartItemRegister(
        Long cartId,
        @NotNull(message = "Campo \"productId\" es requerido")
        Long productId,
        @NotNull(message = "Campo \"quantity\" es requerido")
        Integer quantity
) {
}
