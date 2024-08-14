package com.api.zorvanz.domain.category;

public record CategoryData(
        Long categoryId,
        CategoryName categoryName
) {
    public CategoryData ( Categories category ) {
        this(
                category.getId(),
                category.getCategoryName()
        );
    }
}
