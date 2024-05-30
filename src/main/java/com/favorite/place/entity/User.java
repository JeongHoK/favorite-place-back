package com.favorite.place.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 16)
    private String phoneNumber;

    @Column(length = 128)
    private String email;

    @Column(length = 128)
    private String password;

    @Column(length = 8)
    private String userRole;

    private boolean delYn = false;

    @Builder
    public User(String name, String phoneNumber, String email, String password, String userRole) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.userRole = userRole;

    }

}
