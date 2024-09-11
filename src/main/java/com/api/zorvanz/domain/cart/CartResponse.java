package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItemResponse;
import com.api.zorvanz.domain.category.CategoryData;
import com.api.zorvanz.domain.products.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        Long id,
        BigDecimal totalAmount,
        List <CartItemResponse> cartItems
) {
    public CartResponse ( Cart cart ) {
        this(
                cart.getId(),
                cart.getTotalAmount(),
                cart.getCartItems()
                        .stream()
                        .map( cartItem -> new CartItemResponse(
                                cartItem.getId(),
                                new ProductResponse(
                                        cartItem.getProduct().getId(),
                                        cartItem.getProduct().getName(),
                                        cartItem.getProduct().getDescription(),
                                        new CategoryData(
                                                cartItem.getProduct().getCategory().getId(),
                                                cartItem.getProduct().getCategory().getCategoryName()
                                        ),
                                        cartItem.getProduct().getPrice(),
                                        cartItem.getProduct().getImageUrl()
                                ),
                                cartItem.getCart().getId(),
                                cartItem.getQuantity(),
                                cartItem.getTotalPrice(),
                                cartItem.getUnitPrice() )
                        )
                        .toList()
        );
    }
}
