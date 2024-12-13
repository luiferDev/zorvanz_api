package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.customer.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	@Query ( "SELECT c FROM Customer c WHERE c.id = :customerId" )
	Optional < Customer > findByCustomerId ( @Param ( "customerId" ) @NotNull Long customerId );
	//@Query ( "SELECT c FROM Cart c WHERE c.customer.id = :customerId" )
	Optional < Cart > findCartByCustomerId ( @Param ( "customerId" ) @NotNull Long customerId );
}
