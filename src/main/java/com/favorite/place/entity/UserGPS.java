package com.favorite.place.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_gps")
public class UserGPS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_gps_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private double longitude;

    private double latitude;

    private boolean isDeleted;
}
