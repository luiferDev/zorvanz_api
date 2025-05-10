package com.api.zorvanz.controller;

import com.api.zorvanz.domain.users.*;
import com.api.zorvanz.infra.security.AuthResponse;
import com.api.zorvanz.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping ( "/api/auth" )
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public AuthController ( UserService userService, AuthenticationManager authenticationManager ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // TODO: arreglar el register y el refresh
    @PostMapping ( "/register" )
    @Async ( "threadPoolTaskExecutor" )
    public CompletableFuture < ResponseEntity < UserResponse > > register (
            @Valid @RequestBody RegistrationRequest req ) {
        return userService.registerUser ( req )
                .thenApply ( user -> ResponseEntity.ok ().body ( user ) )
                .exceptionally ( ex -> {
                    // Puedes personalizar la respuesta de error si lo deseas
                    return ResponseEntity.badRequest ().build ();
                } );
    }
    
    /**
     * Endpoint for registering admin users.
     * Only existing admins can create new admin users.
     */
    @PostMapping ( "/register-admin" )
    @Secured("ROLE_ADMIN")
    @Async ( "threadPoolTaskExecutor" )
    public CompletableFuture < ResponseEntity < UserResponse > > registerAdmin (
            @Valid @RequestBody RegistrationRequest req ) {
        return userService.registerAdmin ( req )
                .thenApply ( user -> ResponseEntity.ok ().body ( user ) )
                .exceptionally ( ex -> {
                    return ResponseEntity.badRequest ().build ();
                } );
    }

    @PostMapping ( "/refresh" )
    @Async ( "threadPoolTaskExecutor" )
    public CompletableFuture < ResponseEntity < AuthResponse > > refresh (
            @RequestParam String refreshToken ) {
        return userService.refreshAccessToken ( refreshToken )
                .thenApply ( accessTokenInfo -> {
                    String accessToken = accessTokenInfo.getLeft();
                    Role role = accessTokenInfo.getRight();
                    return ResponseEntity.ok(
                            new AuthResponse(accessToken, refreshToken, "Bearer", role ) );
                })
                .exceptionally ( ex -> ResponseEntity.badRequest ().build () );
    }

    @PostMapping ( "/login" )
    public ResponseEntity < AuthResponse > autenticarUsuario ( @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario ) {
        try {
            // Create authentication token with username and password
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    datosAutenticacionUsuario.username(), datosAutenticacionUsuario.password());
            
            // Authenticate the user
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            
            // Get the authenticated user
            User user = (User) usuarioAutenticado.getPrincipal();
            
            // Generate tokens
            var accessToken = tokenService.generarAccessToken(user);
            var refreshToken = tokenService.generarRefreshToken(user);
            
            // Return response with tokens and user role
            return ResponseEntity.ok(
                    new AuthResponse(
                            accessToken, refreshToken, "Bearer", user.getRole() ) );
        } catch (Exception e) {
            // Log the error
            System.out.println("Authentication error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
