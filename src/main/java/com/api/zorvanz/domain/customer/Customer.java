package com.api.zorvanz.domain.customer;

import jakarta.persistence.*;
import lombok.*;

@Entity (name = "Customer")
@Table (name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Customer {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
