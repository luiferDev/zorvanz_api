package com.api.zorvanz.domain.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddCustomerData(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        String phoneNumber,
        String address
) {
}
