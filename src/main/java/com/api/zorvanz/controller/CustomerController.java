package com.api.zorvanz.controller;

import com.api.zorvanz.domain.customer.AddCustomerData;
import com.api.zorvanz.domain.customer.CustomerRepository;
import com.api.zorvanz.domain.customer.RegisterCustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private RegisterCustomerService customerService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @PostMapping
    @Transactional
    public ResponseEntity registerCustomer(
            @RequestBody @Valid AddCustomerData customerData
            ) {
        var response = customerService.registerCustomer( customerData );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping ResponseEntity getAllCustomers() {
        var customers = customerRepository.findAll() ;
        return ResponseEntity.ok(customers);
    }
}
