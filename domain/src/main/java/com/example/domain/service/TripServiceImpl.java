package com.example.domain.service;

import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.exception.InvalidSeatNumberException;
import com.example.domain.port.TripRepository;
import com.example.domain.port.TripService;
import com.example.domain.port.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private UserRepository userRepository;

    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(String startingPoint, String endPoint, String localDate, String localTime, int availableSeats, int distance, int userId) throws InvalidIdException, InvalidSeatNumberException {
        if (startingPoint.isEmpty() || endPoint.isEmpty() || distance <= 0) {
            throw new RuntimeException("Paramètre(s) invalide(s)");
        }
        if (availableSeats <= 0) {
            throw new InvalidSeatNumberException();
        }
        if (userId <= 0) {
            throw new InvalidIdException(userId);
        }
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDateFormatted = LocalDate.parse(localDate, dateFormatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTimeFormatted = LocalTime.parse(localTime, timeFormatter);


            User user = userRepository.findById(userId);
            List<Reservation> reservationList = new ArrayList<>();
            Trip trip = new Trip(startingPoint, endPoint, localDateFormatted, localTimeFormatted, availableSeats, distance, user, reservationList);
            tripRepository.save(trip);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Trip findById(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Trip trip = tripRepository.findById(id);
            return trip;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Trip> findAll() {
        try {
            return tripRepository.findAll();
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
            Trip trip = tripRepository.findById(id);
            tripRepository.delete(trip);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Trip trip) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Trip tripToUpdate = tripRepository.findById(id);

            tripToUpdate.setStartingPoint(trip.getStartingPoint());
            tripToUpdate.setEndPoint(trip.getEndPoint());
            tripToUpdate.setAvailableSeats(trip.getAvailableSeats());
            tripToUpdate.setDistance(trip.getDistance());
            tripToUpdate.setUser(trip.getUser());
            tripToUpdate.setReservationList(trip.getReservationList());

            tripRepository.save(tripToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int[] calcTripNumbers() {
        // Récupération de tous les trajets
        List<Trip> trips = this.findAll();
        // Récupération date d'aujourd'hui
        LocalDate today = LocalDate.now();

        int doneTrips = 0;
        int notDoneTrips = 0;
        int totalKm = 0;
        for (Trip trip : trips) {
            int compareValue = today.compareTo(trip.getLocalDate());
            if (compareValue>0) {
                ++doneTrips;
                totalKm += trip.getDistance();
            } else {
                ++notDoneTrips;
            }
        }
        int[] tripsNumbers = {doneTrips, notDoneTrips, totalKm};
        return tripsNumbers;
    }

}
