package com.api.zorvanz.domain.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity ( name = "Users" )
@Table ( name = "users" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class User implements UserDetails {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String userName;
    private String email;
    private String role;

    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities () {
        return List.of ( new SimpleGrantedAuthority ( "ROLE_USER" ) );
    }

    @Override
    public String getPassword () {
        return password;
    }

    @Override
    public String getUsername () {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}
