package com.example.adapters.controller;

import com.example.adapters.entity.StatDTO;
import com.example.domain.port.CarService;
import com.example.domain.port.ReservationService;
import com.example.domain.port.TripService;
import com.example.domain.port.UserService;
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

    @GetMapping()
    public ResponseEntity get() {
        StatDTO statDTO = new StatDTO();
        int driversCount = userService.driversCount();
    }
}
