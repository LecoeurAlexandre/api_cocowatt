package com.example.adapters.controller;

import com.example.adapters.entity.StatDTO;
import com.example.domain.port.CarService;
import com.example.domain.port.ReservationService;
import com.example.domain.port.TripService;
import com.example.domain.port.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats")
public class StatController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final TripService tripService;
    private final CarService carService;

    public StatController(UserService userService, ReservationService reservationService, TripService tripService, CarService carService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.tripService = tripService;
        this.carService = carService;
    }

    @Operation(summary = "Récupère les statistiques", description = "Permet de récupèrer un objet contenant des statistiques sur les différents objets de l'API")
    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            StatDTO statDTO = new StatDTO();
            statDTO.setDrivers(carService.findAll().size());
            statDTO.setTravellers(userService.findAll().size() - statDTO.getDrivers());
            statDTO.setElectricPercentage(carService.electricCarsPercentage());
            int[] trips = tripService.calcTripNumbers();
            statDTO.setDoneTrips(trips[0]);
            statDTO.setNotDoneTrips(trips[1]);
            statDTO.setKm(trips[2]);
            statDTO.setBooks(reservationService.findAll().size());
            return ResponseEntity.ok(statDTO);
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}