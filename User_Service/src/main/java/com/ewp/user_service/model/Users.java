package com.ewp.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

//    @NotNull
//    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}