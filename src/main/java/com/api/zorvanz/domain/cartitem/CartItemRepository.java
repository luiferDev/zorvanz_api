package com.api.zorvanz.domain.cartitem;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.customer.Customer;
import com.api.zorvanz.domain.products.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	int findByQuantity ( int quantity );
	
	Product findByProductId ( Long productId );
	
	@Query ( "SELECT c FROM Customer c WHERE c.id = :customerId" )
	Optional < Customer > findByCustomerId ( @Param ( "customerId" ) @NotNull Long customerId );

	List < CartItem > findAllByCustomerId ( Long customerId );

	boolean existsByCustomerIdAndProductId ( Long id, Long id1 );

	Optional < Cart > findByCartId ( @NotNull( message = "Campo \"cart id\" es requerido" ) Long aLong );
}
