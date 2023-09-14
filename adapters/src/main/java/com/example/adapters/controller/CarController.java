package com.example.adapters.controller;

import com.example.adapters.entity.CarDtoRequest;
import com.example.adapters.entity.CarDtoResponse;
import com.example.domain.entity.Car;
import com.example.domain.port.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/car")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Création de voiture", description = "Permet de créer un objet Car")
    @PostMapping("")
    public ResponseEntity<?> post(@RequestParam String brand, @RequestParam String model, @RequestParam int availableSeats, @RequestParam boolean isElectric) {
        try {
            carService.createCar(brand, model, availableSeats, isElectric);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Voiture ajoutée");
    }

    @Operation(summary = "Récupère les voitures", description = "Permet de récupérer une liste avec tout les objets Car")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<CarDtoResponse> carDtoResponseList = new ArrayList<>();
        for (Car c : carService.findAll()) {
            CarDtoResponse carDtoResponse = modelMapper.map(c, CarDtoResponse.class);
            carDtoResponseList.add(carDtoResponse);
        }
        return ResponseEntity.ok(carDtoResponseList);
    }

    @Operation(summary = "Récupère voiture par Id", description = "Permet de récupérer un objet Car ayant pour Id celui indiqué dans le chemin")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            CarDtoResponse carDtoResponse = modelMapper.map(carService.findById(id), CarDtoResponse.class);
            return ResponseEntity.ok(carDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mise à jour voiture", description = "Permet de mettre à jour un objet Car ayant pour Id celui indiqué dans le chemin, en récupérant les attributs de l'objet dans le corps de la requête")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CarDtoRequest carDtoRequest) {
        try {
            Car car = modelMapper.map(carDtoRequest, Car.class);
            carService.update(id, car);
            return ResponseEntity.ok("Voiture mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Suppression de voiture", description = "Supprime l'objet Car ayant pour Id celui indiqué dans le chemin")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            carService.delete(id);
            return ResponseEntity.ok("Voiture supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
