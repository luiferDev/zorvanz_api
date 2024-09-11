package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.CategoryData;

import java.math.BigDecimal;

public record ProductListData(
        Long id,
        String name,
        String description,
        BigDecimal price,
        CategoryData category,
        Integer stock,
        Double popularity,
        String imageUrl
) {
    public ProductListData ( Product product ) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new CategoryData( product.getCategory() ),
                product.getStock(),
                product.getPopularity(),
                product.getImageUrl()
        );
    }
}
