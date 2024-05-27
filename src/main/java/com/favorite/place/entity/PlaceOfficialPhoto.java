package com.favorite.place.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_place_official_photo")
public class PlaceOfficialPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_official_photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String photoLocation;
    private boolean delYn;

}
