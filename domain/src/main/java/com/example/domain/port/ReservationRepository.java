package com.example.domain.port;

import com.example.domain.entity.Reservation;

import java.util.List;

public interface ReservationRepository {

    void save(Reservation reservation);
    Reservation findById(int id);

    List<Reservation> findAll();
    void delete(Reservation reservation);
}
