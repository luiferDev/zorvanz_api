package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.Categories;
import com.api.zorvanz.domain.category.UpdateCategory;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductDTO(
		@NotNull
		Long id,
		String name,
		String description,
		BigDecimal price,
		UpdateCategory category,
		Integer stock,
		Double popularity,
		String imageUrl
) {
}
