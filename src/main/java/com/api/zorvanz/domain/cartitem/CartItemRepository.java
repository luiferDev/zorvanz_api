package com.api.zorvanz.domain.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    void deleteAllByCartId ( Long id );
    
    boolean existsByProductIdAndCartId ( Long productId, Long cartId );
}
