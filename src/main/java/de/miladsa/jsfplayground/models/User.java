package de.miladsa.jsfplayground.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String nationalId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();
}
