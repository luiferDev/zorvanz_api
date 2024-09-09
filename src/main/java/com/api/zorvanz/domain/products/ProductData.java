package com.api.zorvanz.domain.products;

public record ProductData(
        Long productId,
        String name,
        String description,
        Double price,
        Long categoryId,
        Integer stock,
        Double popularity,
        String imageUrl
) {
}
