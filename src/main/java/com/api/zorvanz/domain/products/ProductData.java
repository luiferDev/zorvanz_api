package com.api.zorvanz.domain.products;

public record ProductData(
        Long id,
        String name,
        String description,
        Double price,
        Long categoryId,
        Integer stock,
        Double popularity,
        String imageUrl
) {
}
