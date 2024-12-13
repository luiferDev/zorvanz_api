package com.api.zorvanz.strategy.context;

import com.api.zorvanz.strategy.PaymentStrategy;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class PaymentContext {
	private PaymentStrategy paymentStrategy;

	public void pay ( BigDecimal amount ) {
		paymentStrategy.pay(amount);
	}
}
