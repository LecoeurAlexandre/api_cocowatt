package com.example.domain.port;

import com.example.domain.entity.Car;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import com.example.domain.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    void createReservation(User user, Trip trip, Car car);
    Reservation findById(int id) throws InvalidIdException;
    List<Reservation> findAll();
    void update(int id, Reservation reservation) throws InvalidIdException;
    void delete(int id) throws InvalidIdException;
}
