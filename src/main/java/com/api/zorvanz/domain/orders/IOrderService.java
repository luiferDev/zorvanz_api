package com.api.zorvanz.domain.orders;


import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {
	OrderData createOrder( OrderRegister data );
	void generateInvoice(Orders order);
	OrderData getOrderById(Long id);
	List <OrderData> getAllOrders ();
	void processPayment( String paymentContext, BigDecimal amount );
}
