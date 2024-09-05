package com.api.zorvanz.controller;

import com.api.zorvanz.domain.order_details.OrderDetailData;
import com.api.zorvanz.domain.orders.OrderData;
import com.api.zorvanz.domain.orders.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    // Endpoint para crear una nueva orden
    @Transactional
    @PostMapping
    public ResponseEntity <OrderData> createOrder( @RequestBody List <OrderDetailData> orderDetails) {
        try {
            OrderData newOrder = orderService.createOrder(orderDetails);
            return ResponseEntity.ok( newOrder );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    // ordenes por cliente
    @GetMapping
    public ResponseEntity<List<OrderData>> getAllOrders() {
        List<OrderData> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
}
