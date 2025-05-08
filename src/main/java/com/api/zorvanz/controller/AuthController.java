package com.api.zorvanz.controller;

import com.api.zorvanz.domain.users.RegistrationRequest;
import com.api.zorvanz.domain.users.UserService;
import com.api.zorvanz.infra.security.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping ( "/api/auth" )
public class AuthController {
    private final UserService userService;

    public AuthController ( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping ( "/register" )
    public CompletableFuture < ResponseEntity < String > > register (
            @Valid @RequestBody RegistrationRequest req ) {
        return userService.registerUser ( req )
                .thenApply ( authResponse -> ResponseEntity.ok ().body ( authResponse ) )
                .exceptionally ( ex -> ResponseEntity.badRequest ().build () );
    }

    @PostMapping ( "/refresh" )
    public CompletableFuture < ResponseEntity < AuthResponse > > refresh (
            @RequestParam String refreshToken ) {
        return userService.refreshAccessToken ( refreshToken )
                .thenApply ( access -> ResponseEntity
                        .ok ( new AuthResponse ( access, refreshToken, "Bearer" ) ) )
                .exceptionally ( ex -> ResponseEntity.badRequest ().build () );
    }
}
