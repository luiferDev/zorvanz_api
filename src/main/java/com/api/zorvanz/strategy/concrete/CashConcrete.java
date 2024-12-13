package com.api.zorvanz.strategy.concrete;

import com.api.zorvanz.strategy.PaymentStrategy;

import java.math.BigDecimal;

public class CashConcrete implements PaymentStrategy {
	@Override
	public void pay ( BigDecimal amount ) {
		System.out.println("Paid by cash: " + amount);
	}
}
