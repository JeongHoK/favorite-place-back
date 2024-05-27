package com.favorite.place.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_place_review")
public class PlaceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_review_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int scope;
    private String title;
    private String content;
    private boolean delYn;
}
