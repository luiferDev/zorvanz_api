package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    int findByQuantity ( int quantity );
    
    List <Product> findByProductId ( Long productId );
    Cart findByCartId ( Long cartId );
}
