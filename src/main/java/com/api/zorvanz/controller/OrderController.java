package com.api.zorvanz.controller;

import com.api.zorvanz.domain.orders.OrderData;
import com.api.zorvanz.domain.orders.OrderRegister;
import com.api.zorvanz.domain.orders.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    // Endpoint para crear una nueva orden
    @Transactional
    @PostMapping
    public ResponseEntity <OrderData> createOrder( @RequestBody OrderRegister data,
                                                   UriComponentsBuilder  uriBuilder) {
        try {
            OrderData newOrder = orderService.createOrder( data );
            var URI = uriBuilder.path ( "/api/orders/{id}" ).buildAndExpand ( newOrder ).toUri ();
            return ResponseEntity.created ( URI ).body( newOrder );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // todas las ordenes de compra
    @GetMapping( "/all" )
    public ResponseEntity<List<OrderData>> getAllOrders() {
        List<OrderData> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }


    @GetMapping( "/{id}" )
    public ResponseEntity<OrderData> getOrderById(@PathVariable Long id) {
        OrderData order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
}
