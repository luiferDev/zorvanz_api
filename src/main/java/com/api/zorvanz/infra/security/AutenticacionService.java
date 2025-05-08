package com.api.zorvanz.infra.security;

import com.api.zorvanz.domain.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public AutenticacionService ( UserRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        return usuarioRepository.findByUserName ( username );
    }
}
