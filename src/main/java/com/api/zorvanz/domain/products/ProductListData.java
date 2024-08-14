package com.api.zorvanz.domain.products;

public record ProductListData(
        String name,
        String description,
        Double price,
        Long categoryId,
        Integer stock,
        Double popularity,
        String imageUrl
) {
    public ProductListData(Product product) {
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getStock(),
                product.getPopularity(),
                product.getImageUrl()
        );
    }
}
