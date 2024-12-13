package com.api.zorvanz.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByCustomerId ( Long id );
	BigDecimal findTotalAmountByCustomerId ( Long customerId );
	@Query("SELECT c.totalAmount FROM Carts c WHERE c.id = :cartId")
	BigDecimal findTotalAmountById ( @Param ("cartId") Long cartId );
}
