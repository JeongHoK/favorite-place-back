package com.favorite.place.repository;

import com.favorite.place.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {
}
