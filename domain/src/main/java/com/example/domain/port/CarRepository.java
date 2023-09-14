package com.example.domain.port;

import com.example.domain.entity.Car;

import java.util.List;

public interface CarRepository {
    void save(Car car);
    Car findByID(int id);
    List<Car> findAll();
    void delete(Car car);
    int findAllElectricCars();
}
