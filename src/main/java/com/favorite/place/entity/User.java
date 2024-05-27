package com.favorite.place.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String userRole;
    private boolean delYn;

}
