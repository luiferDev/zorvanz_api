package com.api.zorvanz.domain.users;

import java.time.LocalDateTime;

public record UserData(
        Long id,
        Long customerId,
        String username,
        String email,
        String password,
        String role,
        LocalDateTime createdAt
) {
}
