package com.example.infrastructure.repository;

import com.example.infrastructure.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationEntityRepository extends CrudRepository<ReservationEntity, Integer> {
}
