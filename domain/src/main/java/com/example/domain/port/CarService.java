package com.example.domain.port;

import com.example.domain.entity.Car;
import com.example.domain.entity.User;
import com.example.domain.exception.*;

import java.util.List;

public interface CarService {

    void createCar(String brand, String model, int availableSeats, boolean isElectric) throws EmptyParameterException, InvalidSeatsException, NoUserAssignedToCarException, InvalidIdException;
    Car findById(int id) throws InvalidIdException, EntityNotFoundException;
    List<Car> findAll();
    void delete(int id) throws InvalidIdException;
    void update(int id, Car car) throws InvalidIdException;
    double electricCarsPercentage();
}
