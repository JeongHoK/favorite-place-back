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

    private String placeName;
    private double longitude;
    private double latitude;
    private String address;
    private String detailAddress;
    private String callNumber;
    private String placeIntroduction;
    private String openingHours;
    private String regularHoliday;
    private String nickName;
    private boolean delYn;
    private boolean officialYn;

}
