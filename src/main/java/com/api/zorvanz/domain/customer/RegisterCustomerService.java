package com.api.zorvanz.domain.customer;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerData  registerCustomer( AddCustomerData customer ) {
        
        if ( customer.name() == null || customer.name().isEmpty() ) {
            throw new ValidationException( "Nombre es requerido" );
        }
        
        if ( customer.email() == null || customer.email().isEmpty() ) {
            throw new ValidationException( "Email es requerido" );
        }
        
        if ( customerRepository.existsByEmail( customer.email() ) ) {
            throw new ValidationException( "Email ya existente" );
        }
        
        if ( customerRepository.existsByPhoneNumber( customer.phoneNumber() ) ) {
            throw new ValidationException( "Telefono ya existente" );
        }
        
        var addCustomer = new Customer( null, customer.name(), customer.email(),
                customer.phoneNumber(), customer.address() );
        
        customerRepository.save( addCustomer );
        
        return new CustomerData( addCustomer );
    }
}
