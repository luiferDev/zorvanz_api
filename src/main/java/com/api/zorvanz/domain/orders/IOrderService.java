package com.api.zorvanz.domain.orders;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.math.BigDecimal;
import java.util.List;

@EnableAsync
public interface IOrderService {
    OrderData createOrder ( OrderRegister data );

    void generateInvoice ( Orders order );

    OrderData getOrderById ( Long id );

    List < OrderData > getAllOrders ();

    void processPayment ( Payment paymentMethod, BigDecimal amount );
}
