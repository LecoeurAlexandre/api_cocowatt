package com.example.infrastructure.repository;

import com.example.infrastructure.entity.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripEntityRepository extends CrudRepository<TripEntity,Integer> {
}
