package com.example.infrastructure.repository;

import com.example.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
    List<UserEntity> findAllByLastName(String lastName);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByEmail(String email);
}
