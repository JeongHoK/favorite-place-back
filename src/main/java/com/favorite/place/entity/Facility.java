package com.favorite.place.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(columnDefinition="TEXT")
    private String photoLocation;

    private boolean delYn = false;
}
