package com.api.zorvanz.domain.users;

import com.api.zorvanz.infra.security.TokenService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService ( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = new TokenService ();
    }

    @Async
    public CompletableFuture < UserResponse > registerUser ( RegistrationRequest request ) {
        if ( userRepository.existsByUserName ( request.username () ) ) {
            throw new RuntimeException ( "Username is already taken" );
        }
        if ( userRepository.existsByEmail ( request.email () ) ) {
            throw new RuntimeException ( "Email is already in use" );
        }

        User user = new User ();
        user.setUserName ( request.username () );
        user.setEmail ( request.email () );
        user.setName ( request.name () );
        user.setLastName ( request.lastName () );
        user.setPassword ( passwordEncoder.encode ( request.password () ) );
        user.setRole ( String.valueOf ( Role.USER ) );
        userRepository.save ( user );

        UserResponse response = new UserResponse (
                user.getUsername (),
                user.getEmail (),
                user.getName (),
                user.getLastName ()
        );

        return CompletableFuture.completedFuture ( response );
    }

    @Async
    public CompletableFuture < String > refreshAccessToken ( String refreshToken ) {
        String username = tokenService.getSubjectFromRefreshToken ( refreshToken );
        String newAccess = tokenService.generarRefreshToken (
                userRepository.findUserName ( username )
                        .orElseThrow ( () -> new RuntimeException ( "User not found" ) )
        );
        return CompletableFuture.completedFuture ( newAccess );
    }


}
