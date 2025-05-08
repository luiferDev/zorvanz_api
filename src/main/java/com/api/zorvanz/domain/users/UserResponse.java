package com.api.zorvanz.domain.users;

public record UserResponse (
        String username,
        String email,
        String name,
        String lastName
) {
}
