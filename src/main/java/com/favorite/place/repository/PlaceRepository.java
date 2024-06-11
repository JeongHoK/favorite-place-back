package com.favorite.place.repository;

import com.favorite.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    public List<Place> findByUserIdAndIsDeletedFalse(Long userId);
}
