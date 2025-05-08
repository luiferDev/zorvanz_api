package com.api.zorvanz.controller;

import com.api.zorvanz.domain.orders.OrderData;
import com.api.zorvanz.domain.orders.OrderRegister;
import com.api.zorvanz.domain.orders.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping ( "/api/orders" )
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Crea una nueva orden de forma síncrona.
     */
    @Transactional
    @PostMapping
    public ResponseEntity < OrderData > createOrder (
            @RequestBody OrderRegister data,
            UriComponentsBuilder uriBuilder ) {
        try {
            OrderData newOrder = orderService.createOrder ( data );
            var uri = uriBuilder
                    .path ( "/api/orders/{id}" )
                    .buildAndExpand ( newOrder.id () )
                    .toUri ();
            return ResponseEntity.created ( uri ).body ( newOrder );
        } catch ( IllegalArgumentException e ) {
            return ResponseEntity.badRequest ().build ();
        }
    }

    /**
     * Obtiene todas las órdenes, requiere ROLE_USER.
     */
    @Secured ( "ROLE_USER" )
    @GetMapping ( "/all" )
    public ResponseEntity < List < OrderData > > getAllOrders () {
        List < OrderData > orders = orderService.getAllOrders ();
        return ResponseEntity.ok ( orders );
    }

    /**
     * Obtiene una orden por su ID, requiere ROLE_USER.
     */
    @Secured ( "ROLE_USER" )
    @GetMapping ( "/{id}" )
    public ResponseEntity < OrderData > getOrderById ( @PathVariable Long id ) {
        try {
            OrderData order = orderService.getOrderById ( id );
            if ( order == null ) {
                return ResponseEntity.notFound ().build ();
            }
            return ResponseEntity.ok ( order );
        } catch ( EntityNotFoundException e ) {
            return ResponseEntity.notFound ().build ();
        }
    }
}
