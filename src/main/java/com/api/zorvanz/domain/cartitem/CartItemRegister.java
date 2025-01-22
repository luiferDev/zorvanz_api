package com.api.zorvanz.domain.cartitem;

import jakarta.validation.constraints.NotNull;

public record CartItemRegister(
		@NotNull ( message = "Campo \"productId\" es requerido" )
		Long productId,
		@NotNull ( message = "Campo \"quantity\" es requerido" )
		Integer quantity,
		@NotNull ( message = "Campo \"customer id\" es requerido" )
		Long customerId,
		@NotNull ( message = "Campo \"cart id\" es requerido" )
		Long cartId
) {
}
