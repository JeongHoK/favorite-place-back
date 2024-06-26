package com.favorite.place.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_place_review")
public class PlaceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int scope;

    @Column(length = 64)
    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    private boolean isDeleted;
}
