package com.api.zorvanz.domain.customer;

public record GetCustomerData(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String address
) {
    public GetCustomerData(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );
    }
}
