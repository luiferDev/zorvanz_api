package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal ( HttpServletRequest request,
                                      HttpServletResponse response,
                                      FilterChain filterChain ) throws ServletException, IOException {

        var token = recuperarToken ( request );
        if ( token != null ) {
            var subject = tokenService.getSubjectFromAccessToken ( token ); // extrae username del token
            var usuario = userRepository.findByUserName ( subject );

            if ( usuario != null ) {
                var authentication = new UsernamePasswordAuthenticationToken (
                        usuario, null, usuario.getAuthorities () );

                SecurityContextHolder.getContext ().setAuthentication ( authentication );
            }
        }

        filterChain.doFilter ( request, response );
    }

    private String recuperarToken ( HttpServletRequest request ) {
        var authHeader = request.getHeader ( "Authorization" );
        if ( authHeader != null && authHeader.startsWith ( "Bearer " ) ) {
            return authHeader.substring ( 7 );
        }
        return null;
    }
}

