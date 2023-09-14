package com.example.adapters.controller;

import com.example.adapters.entity.TripDtoRequest;
import com.example.adapters.entity.TripDtoResponse;
import com.example.domain.entity.Trip;
import com.example.domain.port.TripService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/trip")
@CrossOrigin("*")
public class TripController {

    private final ModelMapper modelMapper;
    private final TripService tripService;

    public TripController(ModelMapper modelMapper, TripService tripService) {
        this.modelMapper = modelMapper;
        this.tripService = tripService;
    }

    @Operation(summary = "Création de trajet", description = "Permet de créer un objet Trip")
    @PostMapping("")
    public ResponseEntity<?> post(@RequestParam String startingPoint, @RequestParam String endPoint, @RequestParam String localDate, @RequestParam String localTime, @RequestParam int availableSeats, @RequestParam int distance, @RequestParam int userId) {
        try {
            tripService.create(startingPoint, endPoint, localDate, localTime,availableSeats, distance, userId);
            return ResponseEntity.ok("Trajet ajouté");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Récupère les trajets", description = "Permet de récupèrer une liste avec tout les objets Trip")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<TripDtoResponse> tripDtoResponseList = new ArrayList<>();
            for (Trip t : tripService.findAll()) {
                TripDtoResponse tripDtoResponse = modelMapper.map(t, TripDtoResponse.class);
                tripDtoResponseList.add(tripDtoResponse);
            }
            return ResponseEntity.ok(tripDtoResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Récupère trajet par Id", description = "Permet de récupèrer un objet Trip selon son Id")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            TripDtoResponse tripDtoResponse = modelMapper.map(tripService.findById(id), TripDtoResponse.class);
            return ResponseEntity.ok(tripDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Suppression de trajet", description = "Permet de supprimer un objet Trip selon son Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            tripService.delete(id);
            return ResponseEntity.ok("Trajet supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mise à jour de trajet", description = "Permet de mettre à jour un objet Trip")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TripDtoRequest tripDtoRequest) {
        try {
            tripService.update(id, modelMapper.map(tripDtoRequest, Trip.class));
            return ResponseEntity.ok("Trajet mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
