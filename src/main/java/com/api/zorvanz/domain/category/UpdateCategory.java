package com.api.zorvanz.domain.category;

public record UpdateCategory(
		Long categoryId,
		CategoryName categoryName

) {
	public UpdateCategory ( UpdateCategory updateCategory ) {
		this(
				updateCategory.categoryId(),
				updateCategory.categoryName()
		);
	}
}
