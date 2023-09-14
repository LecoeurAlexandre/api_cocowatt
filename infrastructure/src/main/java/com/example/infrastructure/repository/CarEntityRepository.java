package com.example.infrastructure.repository;

import com.example.infrastructure.entity.CarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarEntityRepository extends CrudRepository<CarEntity, Integer> {
    @Query(value="SELECT COUNT(*) FROM car_entity WHERE is_electric = 1", nativeQuery = true)
    int getCarCount();
}
