package com.api.zorvanz.domain.customer;

public record CustomerData(
        Long customerId,
        String name,
        String email,
        String phoneNumber,
        String address
) {
}
