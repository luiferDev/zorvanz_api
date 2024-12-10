package com.api.zorvanz.domain.cart;

import com.api.zorvanz.domain.cartitem.CartItem;
import com.api.zorvanz.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity ( name = "Carts" )
@Table ( name = "carts" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class Cart {
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "customer_id" )
	private Customer customer;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems = new ArrayList <> ();

}

