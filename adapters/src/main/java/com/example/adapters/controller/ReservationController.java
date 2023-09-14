package com.example.adapters.controller;

import com.example.adapters.entity.ReservationDtoRequest;
import com.example.adapters.entity.ReservationDtoResponse;
import com.example.domain.entity.Reservation;
import com.example.domain.port.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
@CrossOrigin("*")
public class ReservationController {
    private final ModelMapper modelMapper;
    private final ReservationService reservationService;

    public ReservationController(ModelMapper modelMapper, ReservationService reservationService) {
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    @Operation(summary = "Création de réservation", description = "Permet de créer un objet Reservation")
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

    @Operation(summary = "Récupère les réservations", description = "Permet de récupèrer une liste avec tout les objets Reservation")
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

    @Operation(summary = "Récupère réservation par id", description = "Permet de récupèrer un objet Reservation selon son Id")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            ReservationDtoResponse reservationDtoResponse = modelMapper.map(reservationService.findById(id), ReservationDtoResponse.class);
            return ResponseEntity.ok(reservationDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Suppression de réservation", description = "Permet de supprimer un objet Reservation selon son Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.ok("Réservation supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mise à jour de réservation", description = "Permet de mettre à jour un objet Reservation")
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
