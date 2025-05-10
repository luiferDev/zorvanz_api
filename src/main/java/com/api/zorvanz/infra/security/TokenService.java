package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.Role;
import com.api.zorvanz.domain.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private static final long ACCESS_TOKEN_EXPIRATION_HOURS = 2;
    private static final long REFRESH_TOKEN_EXPIRATION_HOURS = 48;
    @Value ( "${api.security.secret}" )
    private String apiSecret;
    @Value ( "${api.security.secret}" )
    private String refreshSecret;

    public String generarAccessToken ( User usuario ) {
        try {
            Algorithm algorithm = Algorithm.HMAC256 ( apiSecret );
            return JWT.create ()
                    .withIssuer ( "zorvanz" )
                    .withSubject ( usuario.getUsername () )
                    .withClaim ( "id", usuario.getId () )
                    .withClaim ( "role", usuario.getRole().name() )
                    .withExpiresAt ( generarFechaExpiracionAccess () )
                    .sign ( algorithm );
        } catch ( JWTCreationException exception ) {
            throw new RuntimeException ( "Error al crear Access Token", exception );
        }
    }

    public String generarRefreshToken ( User usuario ) {
        try {
            Algorithm algorithm = Algorithm.HMAC256 ( refreshSecret );
            return JWT.create ()
                    .withIssuer ( "zorvanz" )
                    .withSubject ( usuario.getUsername () )
                    .withClaim ( "id", usuario.getId () )
                    .withClaim ( "role", usuario.getRole().name() )
                    .withExpiresAt ( generarFechaExpiracionRefresh () )
                    .sign ( algorithm );
        } catch ( JWTCreationException exception ) {
            throw new RuntimeException ( "Error al crear Refresh Token", exception );
        }
    }

    public String getSubjectFromAccessToken ( String token ) {
        return getSubject ( token );
    }

    public String getSubjectFromRefreshToken ( String token ) {
        return getSubject ( token );
    }

    public String getSubject ( String token ) {
        if ( token == null ) {
            throw new RuntimeException ();
        }
        DecodedJWT decoded;
        try {
            Algorithm algorithm = Algorithm.HMAC256 ( apiSecret );
            decoded = JWT.require ( algorithm )
                    .withIssuer ( "zorvanz" )
                    .build ()
                    .verify ( token );
        } catch ( JWTVerificationException exception ) {
            System.out.println ( "Token inválido: " + exception.getMessage () );
            throw new RuntimeException ( "Token inválido", exception );
        }
        if ( decoded.getSubject () == null ) {
            throw new RuntimeException ( "Verifier invalido" );
        }
        return decoded.getSubject ();
    }

    /**
     * Extracts the user role from the JWT token
     * @param token The JWT token
     * @return The user role (ROLE_ADMIN or ROLE_USER)
     */
    public Role getRoleFromToken(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }
        DecodedJWT decoded;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            decoded = JWT.require(algorithm)
                    .withIssuer("zorvanz")
                    .build()
                    .verify(token);
            
            String roleName = decoded.getClaim("role").asString();
            return Role.valueOf(roleName);
        } catch (JWTVerificationException exception) {
            System.out.println("Token inválido: " + exception.getMessage());
            throw new RuntimeException("Token inválido", exception);
        } catch (IllegalArgumentException exception) {
            System.out.println("Role inválido: " + exception.getMessage());
            throw new RuntimeException("Role inválido en token", exception);
        }
    }

    /**
     * Checks if the user has admin role based on the token
     * @param token The JWT token
     * @return true if the user has ROLE_ADMIN, false otherwise
     */
    public boolean isAdmin(String token) {
        return getRoleFromToken(token) == Role.ROLE_ADMIN;
    }

    /**
     * Checks if the user has user role based on the token
     * @param token The JWT token
     * @return true if the user has ROLE_USER, false otherwise
     */
    public boolean isUser(String token) {
        return getRoleFromToken(token) == Role.ROLE_USER;
    }

    private Instant generarFechaExpiracionAccess () {
        return LocalDateTime.now ()
                .plusHours ( ACCESS_TOKEN_EXPIRATION_HOURS )
                .toInstant ( ZoneOffset.of ( "-05:00" ) );
    }

    private Instant generarFechaExpiracionRefresh () {
        return LocalDateTime.now ()
                .plusHours ( REFRESH_TOKEN_EXPIRATION_HOURS )
                .toInstant ( ZoneOffset.of ( "-05:00" ) );
    }
}
