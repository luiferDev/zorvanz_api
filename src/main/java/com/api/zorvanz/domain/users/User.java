package com.api.zorvanz.domain.users;

import jakarta.persistence.*;
import lombok.*;

@Entity ( name = "Users" )
@Table ( name = "users" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode ( of = "id" )
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastName;
	private String password;
	private String userName;
	private String email;
	private String role;
}
