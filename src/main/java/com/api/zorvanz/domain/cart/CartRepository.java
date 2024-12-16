package com.api.zorvanz.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{
    List <Cart> findByCustomerId ( Long id );
	@Query("SELECT c.totalAmount FROM Carts c WHERE c.id = :cartId")
	BigDecimal findTotalAmountById ( @Param ("cartId") Long cartId );
}
