package com.api.zorvanz.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableAsync
@EnableMethodSecurity ( securedEnabled = true )
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations ( SecurityFilter securityFilter ) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain ( HttpSecurity httpSecurity ) throws Exception {
        return httpSecurity
                .csrf ( AbstractHttpConfigurer :: disable )
                .sessionManagement ( sessionManagement ->
                        sessionManagement.sessionCreationPolicy ( SessionCreationPolicy.STATELESS ) )
                .authorizeHttpRequests ( authorizeRequests ->
                        authorizeRequests
                                .requestMatchers ( HttpMethod.POST, "/api/auth/login" ).permitAll ()
                                .requestMatchers ( HttpMethod.POST, "/api/auth/register" ).permitAll ()
                                //.requestMatchers ( "/api/products/create-product" ).hasRole ( "ADMIN" )
                                .requestMatchers ( HttpMethod.POST, "/api/auth/refresh" ).permitAll ()
                                .requestMatchers ( HttpMethod.GET, "/api/products/search" ).permitAll ()
                                .requestMatchers ( HttpMethod.GET, "/api/products" ).permitAll ()
                                .requestMatchers ( HttpMethod.GET, "/api/products/{id}" ).permitAll ()
//                                .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN") /// para eliminar necesita el perfirl admin
//                                .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
                                .requestMatchers ( "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**" )
                                .permitAll ()
                                .anyRequest ().authenticated () )
                .addFilterBefore ( securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build ();
    }


    @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager ();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder ();
    }

}
