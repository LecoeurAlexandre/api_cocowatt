package com.example.domain.port;

import com.example.domain.entity.Trip;

import java.util.List;

public interface TripRepository {

    void save(Trip trip);
    Trip findById(int id);
    List<Trip> findAll();
    void delete(Trip trip);
}
