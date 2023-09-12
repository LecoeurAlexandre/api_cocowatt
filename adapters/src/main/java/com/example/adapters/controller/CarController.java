package com.example.adapters.controller;

import com.example.adapters.entity.CarDtoRequest;
import com.example.adapters.entity.CarDtoResponse;
import com.example.domain.entity.Car;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.port.CarService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity post(@RequestParam String brand, @RequestParam String model, @RequestParam int availableSeats, @RequestParam boolean isElectric) {
        try {
            carService.createCar(brand, model, availableSeats, isElectric);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Voiture ajoutée");
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        List<CarDtoResponse> carDtoResponseList = new ArrayList<>();
        for (Car c : carService.findAll()) {
            CarDtoResponse carDtoResponse = modelMapper.map(c, CarDtoResponse.class);
            carDtoResponseList.add(carDtoResponse);
        }
        return ResponseEntity.ok(carDtoResponseList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable int id) throws InvalidIdException {
        try {
            CarDtoResponse carDtoResponse = modelMapper.map(carService.findById(id), CarDtoResponse.class);
            return ResponseEntity.ok(carDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody CarDtoRequest carDtoRequest) throws InvalidIdException {
        try {
            Car car = modelMapper.map(carDtoRequest, Car.class);
            carService.update(id, car);
            return ResponseEntity.ok("Voiture mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) throws InvalidIdException {
        try {
            carService.delete(id);
            return ResponseEntity.ok("Voiture supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
