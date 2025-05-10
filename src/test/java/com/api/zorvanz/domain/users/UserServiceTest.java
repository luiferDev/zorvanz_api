package com.api.zorvanz.domain.users;

import com.api.zorvanz.infra.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith ( MockitoExtension.class )
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;
    private TokenService tokenService;

    @BeforeEach
    void setUp () {
        userService = new UserService ( userRepository, passwordEncoder, tokenService );
    }

    @Test
    void registerUserShouldCreateUserWithUserRole () throws ExecutionException, InterruptedException {
        // Given
        RegistrationRequest request = new RegistrationRequest (
                "testuser",
                "test@example.com",
                "Test",
                "User",
                "password"
        );

        when ( userRepository.existsByUserName ( request.username () ) ).thenReturn ( false );
        when ( userRepository.existsByEmail ( request.email () ) ).thenReturn ( false );
        when ( passwordEncoder.encode ( request.password () ) ).thenReturn ( "encodedPassword" );

        // When
        CompletableFuture < UserResponse > futureResponse = userService.registerUser ( request );
        UserResponse response = futureResponse.get ();

        // Then
        ArgumentCaptor < User > userCaptor = ArgumentCaptor.forClass ( User.class );
        verify ( userRepository ).save ( userCaptor.capture () );

        User savedUser = userCaptor.getValue ();
        assertEquals ( request.username (), savedUser.getUsername () );
        assertEquals ( request.email (), savedUser.getEmail () );
        assertEquals ( request.name (), savedUser.getName () );
        assertEquals ( request.lastName (), savedUser.getLastName () );
        assertEquals ( "encodedPassword", savedUser.getPassword () );
        assertEquals ( Role.ROLE_USER, savedUser.getRole () );

        assertEquals ( request.username (), response.username () );
        assertEquals ( request.email (), response.email () );
        assertEquals ( request.name (), response.name () );
        assertEquals ( request.lastName (), response.lastName () );
    }

    @Test
    void registerAdminShouldCreateUserWithAdminRole () throws ExecutionException, InterruptedException {
        // Given
        RegistrationRequest request = new RegistrationRequest (
                "adminuser",
                "admin@example.com",
                "Admin",
                "User",
                "adminpass"
        );

        when ( userRepository.existsByUserName ( request.username () ) ).thenReturn ( false );
        when ( userRepository.existsByEmail ( request.email () ) ).thenReturn ( false );
        when ( passwordEncoder.encode ( request.password () ) ).thenReturn ( "encodedAdminPassword" );

        // When
        CompletableFuture < UserResponse > futureResponse = userService.registerAdmin ( request );
        UserResponse response = futureResponse.get ();

        // Then
        ArgumentCaptor < User > userCaptor = ArgumentCaptor.forClass ( User.class );
        verify ( userRepository ).save ( userCaptor.capture () );

        User savedUser = userCaptor.getValue ();
        assertEquals ( request.username (), savedUser.getUsername () );
        assertEquals ( request.email (), savedUser.getEmail () );
        assertEquals ( request.name (), savedUser.getName () );
        assertEquals ( request.lastName (), savedUser.getLastName () );
        assertEquals ( "encodedAdminPassword", savedUser.getPassword () );
        assertEquals ( Role.ROLE_ADMIN, savedUser.getRole () );

        assertEquals ( request.username (), response.username () );
        assertEquals ( request.email (), response.email () );
        assertEquals ( request.name (), response.name () );
        assertEquals ( request.lastName (), response.lastName () );
    }

    @Test
    void registerUserShouldThrowExceptionWhenUsernameExists () {
        // Given
        RegistrationRequest request = new RegistrationRequest (
                "existinguser",
                "test@example.com",
                "Test",
                "User",
                "password"
        );

        when ( userRepository.existsByUserName ( request.username () ) ).thenReturn ( true );

        // When & Then
        assertThrows ( RuntimeException.class, () -> userService.registerUser ( request ) );
        verify ( userRepository, never () ).save ( any () );
    }

    @Test
    void registerUserShouldThrowExceptionWhenEmailExists () {
        // Given
        RegistrationRequest request = new RegistrationRequest (
                "testuser",
                "existing@example.com",
                "Test",
                "User",
                "password"
        );

        when ( userRepository.existsByUserName ( request.username () ) ).thenReturn ( false );
        when ( userRepository.existsByEmail ( request.email () ) ).thenReturn ( true );

        // When & Then
        assertThrows ( RuntimeException.class, () -> userService.registerUser ( request ) );
        verify ( userRepository, never () ).save ( any () );
    }
}