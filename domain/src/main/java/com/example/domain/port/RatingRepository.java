package com.example.domain.port;

import com.example.domain.entity.Rating;

import java.util.List;

public interface RatingRepository {
    void save(Rating rating);
    Rating findById(String id);
    List<Rating> findAll();
    void delete(Rating rating);
    List<Rating> findAllByUserId(int id);
}
