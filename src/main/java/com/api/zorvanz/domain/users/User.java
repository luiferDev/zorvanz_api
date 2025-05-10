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

    @Enumerated ( EnumType.STRING )
    private Role role;

    /**
     * Creates a new user with the USER role
     */
    public static User createUser ( String name, String lastName,
                                    String password, String userName,
                                    String email ) {
        User user = new User ();
        user.setName ( name );
        user.setLastName ( lastName );
        user.setPassword ( password );
        user.setUserName ( userName );
        user.setEmail ( email );
        user.setRole ( Role.ROLE_USER );
        return user;
    }

    /**
     * Creates a new user with the ADMIN role
     */
    public static User createAdmin ( String name, String lastName,
                                     String password, String userName, String email ) {
        User user = new User ();
        user.setName ( name );
        user.setLastName ( lastName );
        user.setPassword ( password );
        user.setUserName ( userName );
        user.setEmail ( email );
        user.setRole ( Role.ROLE_ADMIN );
        return user;
    }

    /**
     * Returns the authorities granted to the user based on their role.
     * This method is used by Spring Security to determine user permissions.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities () {
        return List.of ( new SimpleGrantedAuthority ( "ROLE_" + role ) );
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
