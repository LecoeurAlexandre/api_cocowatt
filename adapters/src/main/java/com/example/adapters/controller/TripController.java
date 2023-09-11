package com.example.adapters.controller;

import com.example.adapters.entity.TripDtoRequest;
import com.example.adapters.entity.TripDtoResponse;
import com.example.domain.entity.Trip;
import com.example.domain.port.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/trip")
public class TripController {

    private final ModelMapper modelMapper;
    private final TripService tripService;

    public TripController(ModelMapper modelMapper, TripService tripService) {
        this.modelMapper = modelMapper;
        this.tripService = tripService;
    }

    @PostMapping("")
    public ResponseEntity post(@RequestParam String startingPoint, @RequestParam String endPoint, @RequestParam String localDate, @RequestParam String localTime, @RequestParam int availableSeats, @RequestParam int distance, @RequestParam int userId) {
        try {
            tripService.create(startingPoint, endPoint, localDate, localTime,availableSeats, distance, userId);
            return ResponseEntity.ok("Trajet ajouté");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
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

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        try {
            TripDtoResponse tripDtoResponse = modelMapper.map(tripService.findById(id), TripDtoResponse.class);
            return ResponseEntity.ok(tripDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            tripService.delete(id);
            return ResponseEntity.ok("Trajet supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody TripDtoRequest tripDtoRequest) {
        try {
            tripService.update(id, modelMapper.map(tripDtoRequest, Trip.class));
            return ResponseEntity.ok("Trajet mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
