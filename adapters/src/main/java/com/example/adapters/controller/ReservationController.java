package com.example.adapters.controller;

import com.example.adapters.entity.ReservationDtoRequest;
import com.example.adapters.entity.ReservationDtoResponse;
import com.example.domain.entity.Reservation;
import com.example.domain.port.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    private final ModelMapper modelMapper;
    private final ReservationService reservationService;

    public ReservationController(ModelMapper modelMapper, ReservationService reservationService) {
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }


    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody ReservationDtoRequest reservationDtoRequest) {
        try {
            reservationService.createReservation(
                    reservationDtoRequest.getUser(),
                    reservationDtoRequest.getTrip(),
                    reservationDtoRequest.getCar()
            );
            return ResponseEntity.ok("Réservation effectuée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<ReservationDtoResponse> reservationDtoResponseList = new ArrayList<>();
            for (Reservation r : reservationService.findAll()) {
                ReservationDtoResponse reservationDtoResponse = modelMapper.map(r, ReservationDtoResponse.class);
                reservationDtoResponseList.add(reservationDtoResponse);
            }
            return ResponseEntity.ok(reservationDtoResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            ReservationDtoResponse reservationDtoResponse = modelMapper.map(reservationService.findById(id), ReservationDtoResponse.class);
            return ResponseEntity.ok(reservationDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.ok("Réservation supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ReservationDtoRequest reservationDtoRequest) {
        try {
            reservationService.update(id, modelMapper.map(reservationDtoRequest, Reservation.class));
            return ResponseEntity.ok("Réservation mise à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
