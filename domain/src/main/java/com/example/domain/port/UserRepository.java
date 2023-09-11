package com.example.domain.port;

import com.example.domain.entity.User;

import java.util.List;

public interface UserRepository {

    void save(User user);
    User findById(int id);
    List<User> findAll();
    void delete(User user);
    List<User> findByLastName(String lastName);
}
