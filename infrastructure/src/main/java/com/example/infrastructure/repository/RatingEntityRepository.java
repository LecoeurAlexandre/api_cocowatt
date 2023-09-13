package com.example.infrastructure.repository;

import com.example.infrastructure.entity.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingEntityRepository extends MongoRepository<RatingEntity, String> {
    List<RatingEntity> findAllByUserId(int userId);
}
