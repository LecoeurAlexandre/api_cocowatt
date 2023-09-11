package com.example.infrastructure.repository;

import com.example.infrastructure.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEntityRepository extends CrudRepository<CarEntity, Integer> {
}
