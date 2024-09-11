package com.api.zorvanz.domain.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegisterProductData(
        @NotBlank(message = "nombre es obligatorio")
        String name,
        @NotBlank
        String description,
        @NotNull(message = "Campo \"precio\" es requerido")
        BigDecimal price,
        @NotNull
        Long categoryId,
        @NotNull(message = "Campo \"stock\" es requerido")
        Integer stock,
        @NotNull
        Double popularity,
        @NotBlank
        String imageUrl
) {
}
