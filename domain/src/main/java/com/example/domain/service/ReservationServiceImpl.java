package com.example.domain.service;

import com.example.domain.entity.Car;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.port.ReservationRepository;
import com.example.domain.port.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationServiceImpl implements ReservationService{

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void createReservation(User user, Trip trip, Car car) {
        if (user.equals(null) || trip.equals(null) || car.equals(null)) {
            throw new RuntimeException("Paramètre(s) invalide(s)");
        }
        try {
            LocalDate date = LocalDate.now();
            Reservation reservation = new Reservation(user, trip, car, date);
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Reservation findById(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Reservation reservation = reservationRepository.findById(id);
            return reservation;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Reservation> findAll() {
        try {
            return reservationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Reservation reservation) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Reservation reservationToUpdate = reservationRepository.findById(id);

            reservationToUpdate.setCar(reservation.getCar());
            reservationToUpdate.setDate(reservation.getDate());
            reservationToUpdate.setUser(reservation.getUser());
            reservationToUpdate.setTrip(reservation.getTrip());

            reservationRepository.save(reservationToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Reservation reservation = reservationRepository.findById(id);
            reservationRepository.delete(reservation);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
