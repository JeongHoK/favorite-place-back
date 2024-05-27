package com.favorite.place.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private String name;
    private String phoneNumber;
    private String email;
    private String password;
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
