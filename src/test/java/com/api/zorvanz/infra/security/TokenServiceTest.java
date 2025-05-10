package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.Role;
import com.api.zorvanz.domain.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private TokenService tokenService;
    private User adminUser;
    private User regularUser;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        // Set the API secret for testing
        ReflectionTestUtils.setField(tokenService, "apiSecret", "test-secret-key-for-jwt-token-generation-and-validation");
        ReflectionTestUtils.setField(tokenService, "refreshSecret", "test-secret-key-for-jwt-token-generation-and-validation");

        // Create test users
        adminUser = User.createAdmin("Admin", "User", "password", "admin", "admin@example.com");
        regularUser = User.createUser("Regular", "User", "password", "user", "user@example.com");
    }

    @Test
    void shouldGenerateAccessTokenWithUserRole() {
        // When
        String token = tokenService.generarAccessToken(regularUser);

        // Then
        assertNotNull(token);
        assertEquals(Role.ROLE_USER, tokenService.getRoleFromToken(token));
        assertTrue(tokenService.isUser(token));
        assertFalse(tokenService.isAdmin(token));
    }

    @Test
    void shouldGenerateAccessTokenWithAdminRole() {
        // When
        String token = tokenService.generarAccessToken(adminUser);

        // Then
        assertNotNull(token);
        assertEquals(Role.ROLE_ADMIN, tokenService.getRoleFromToken(token));
        assertTrue(tokenService.isAdmin(token));
        assertFalse(tokenService.isUser(token));
    }

    @Test
    void shouldExtractUsernameFromToken() {
        // When
        String token = tokenService.generarAccessToken(regularUser);

        // Then
        String username = tokenService.getSubject(token);
        assertEquals(regularUser.getUsername(), username);
    }

    @Test
    void shouldGenerateRefreshTokenWithRole() {
        // When
        String token = tokenService.generarRefreshToken(adminUser);

        // Then
        assertNotNull(token);
        assertEquals(Role.ROLE_ADMIN, tokenService.getRoleFromToken(token));
    }
}