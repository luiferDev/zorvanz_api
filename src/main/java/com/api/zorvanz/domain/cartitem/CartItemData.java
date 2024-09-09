package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.products.ProductListData;

public record CartItemData(
        Long id,
        Long cartId,
        ProductListData product,
        Integer quantity
) {
}
