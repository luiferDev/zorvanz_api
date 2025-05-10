package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.Role;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Role role
) {}