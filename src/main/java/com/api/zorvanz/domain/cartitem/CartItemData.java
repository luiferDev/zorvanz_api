package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.products.ProductListData;

public record CartItemData(
        Long id,
        Long cartId,
        ProductListData product,
        Integer quantity
) {
    public CartItemData ( CartItem cartItem ) {
        this(
                cartItem.getId(),
                cartItem.getCart().getId(),
                new ProductListData ( cartItem.getProduct() ),
                cartItem.getQuantity()
        );
    }
}
