package com.api.zorvanz.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    boolean existsByEmail ( String email );
    
    boolean existsByPhoneNumber ( String phoneNumber );
}
