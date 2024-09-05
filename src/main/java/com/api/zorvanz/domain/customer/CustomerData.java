package com.api.zorvanz.domain.customer;

public record CustomerData(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String address
) {
    public CustomerData ( Customer addCustomer ) {
        this(
                addCustomer.getId(),
                addCustomer.getName(),
                addCustomer.getEmail(),
                addCustomer.getPhoneNumber(),
                addCustomer.getAddress()
        );
    }
}
