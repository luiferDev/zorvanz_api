package com.api.zorvanz.domain.users;

import java.util.Date;

public record UserData(
        Long id,
        Long customerId,
        String username,
        String email,
        String password,
        String role,
        Date createdAt
) {
}
