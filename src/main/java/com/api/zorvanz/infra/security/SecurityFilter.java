package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository usuarioRepository;

    public SecurityFilter ( TokenService tokenService, UserRepository usuarioRepository ) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal ( HttpServletRequest request,
                                      HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader ( "Authorization" );
        if ( authHeader != null ) {
            var token = authHeader.replace ( "Bearer ", "" );
            var nombreUsuario = tokenService.getSubject ( token ); // extract username
            if ( nombreUsuario != null ) {
                // Token valido
                var usuario = usuarioRepository.findByUserName ( nombreUsuario );
                var authentication = new UsernamePasswordAuthenticationToken ( usuario, null,
                        usuario.getAuthorities () ); // Forzamos un inicio de sesion
                SecurityContextHolder.getContext ().setAuthentication ( authentication );
            }
        }
        filterChain.doFilter ( request, response );
    }
}
