package com.example.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
