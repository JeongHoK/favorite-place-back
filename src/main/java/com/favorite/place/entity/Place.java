package com.favorite.place.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 64)
    private String placeName;

    private double longitude;

    private double latitude;

    @Column(length = 512)
    private String address;

    @Column(length = 256)
    private String detailAddress;

    @Column(length = 16)
    private String callNumber;

    @Column(length = 512)
    private String placeIntroduction;

    @Column(length = 256)
    private String openingHours;

    @Column(length = 256)
    private String regularHoliday;

    @Column(length = 64)
    private String nickName;

    private boolean isDeleted;

    private boolean officialYn = false;

}
