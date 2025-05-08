package com.api.zorvanz.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository < User, Long > {
    UserDetails findByUserName ( String username );

    @Query ( "SELECT u FROM Users u WHERE u.userName = :username" )
    Optional < User > findUserName ( String username );

    Optional < User > findByEmail ( String email );

    boolean existsByUserName ( String username );

    boolean existsByEmail ( String email );
}

