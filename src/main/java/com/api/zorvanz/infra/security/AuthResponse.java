package com.api.zorvanz.infra.security;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {}