package com.api.zorvanz.domain.users;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUserShouldSetRoleUser () {
        // Given
        String name = "John";
        String lastName = "Doe";
        String password = "password";
        String userName = "johndoe";
        String email = "john@example.com";

        // When
        User user = User.createUser ( name, lastName, password, userName, email );

        // Then
        assertEquals ( Role.ROLE_USER, user.getRole () );
        assertEquals ( name, user.getName () );
        assertEquals ( lastName, user.getLastName () );
        assertEquals ( password, user.getPassword () );
        assertEquals ( userName, user.getUsername () );
        assertEquals ( email, user.getEmail () );
        assertFalse ( user.isAdmin () );
    }

    @Test
    void createAdminShouldSetRoleAdmin () {
        // Given
        String name = "Admin";
        String lastName = "User";
        String password = "adminpass";
        String userName = "adminuser";
        String email = "admin@example.com";

        // When
        User admin = User.createAdmin ( name, lastName, password, userName, email );

        // Then
        assertEquals ( Role.ROLE_ADMIN, admin.getRole () );
        assertEquals ( name, admin.getName () );
        assertEquals ( lastName, admin.getLastName () );
        assertEquals ( password, admin.getPassword () );
        assertEquals ( userName, admin.getUsername () );
        assertEquals ( email, admin.getEmail () );
        assertTrue ( admin.isAdmin () );
    }

    @Test
    void getAuthoritiesShouldReturnCorrectAuthoritiesForUser () {
        // Given
        User user = User.createUser ( "John", "Doe", "password", "johndoe", "john@example.com" );

        // When
        Collection < ? extends GrantedAuthority > authorities = user.getAuthorities ();

        // Then
        assertEquals ( 1, authorities.size () );
        assertTrue ( authorities.stream ()
                .anyMatch ( authority -> authority.getAuthority ().equals ( "ROLE_USER" ) ) );
    }

    @Test
    void getAuthoritiesShouldReturnCorrectAuthoritiesForAdmin () {
        // Given
        User admin = User.createAdmin ( "Admin", "User", "adminpass", "adminuser", "admin@example.com" );

        // When
        Collection < ? extends GrantedAuthority > authorities = admin.getAuthorities ();

        // Then
        assertEquals ( 1, authorities.size () );
        assertTrue ( authorities.stream ()
                .anyMatch ( authority -> authority.getAuthority ().equals ( "ROLE_ADMIN" ) ) );
    }

    @Test
    void isAdminShouldReturnTrueForAdminUser () {
        // Given
        User admin = new User ();
        admin.setRole ( Role.ROLE_ADMIN );

        // When & Then
        assertTrue ( admin.isAdmin () );
    }

    @Test
    void isAdminShouldReturnFalseForRegularUser () {
        // Given
        User user = new User ();
        user.setRole ( Role.ROLE_USER );

        // When & Then
        assertFalse ( user.isAdmin () );
    }
}