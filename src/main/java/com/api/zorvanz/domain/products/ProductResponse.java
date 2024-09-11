package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.CategoryData;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        CategoryData category,
        BigDecimal price,
        String imageUrl
) {
}
