package com.favorite.place.repository;

import com.favorite.place.entity.PlaceBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceBookmarkRepository extends JpaRepository<PlaceBookmark, Long> {
}
