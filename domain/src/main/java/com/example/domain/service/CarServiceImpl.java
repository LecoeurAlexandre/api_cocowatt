package com.example.domain.service;

import com.example.domain.entity.Car;
import com.example.domain.entity.User;
import com.example.domain.exception.EmptyParameterException;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.exception.InvalidSeatsException;
import com.example.domain.exception.NoUserAssignedToCarException;
import com.example.domain.port.CarRepository;
import com.example.domain.port.CarService;
import com.example.domain.port.UserRepository;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private UserRepository userRepository;

    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createCar(String brand, String model, int availableSeats, boolean isElectric) throws EmptyParameterException, InvalidSeatsException, InvalidIdException {
        if (brand.isEmpty() || model.isEmpty()) {
            throw new EmptyParameterException();
        }
        if (availableSeats <= 0) {
            throw new InvalidSeatsException();
        }
        try {
            Car car = new Car(brand, model, availableSeats, isElectric);
            carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Car findById(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        Car car = carRepository.findByID(id);
        return car;
    }

    @Override
    public List<Car> findAll() {
        try {
            return carRepository.findAll();
        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            Car car = carRepository.findByID(id);
            carRepository.delete(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Car car) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }

        try {
            Car carToUpdate = carRepository.findByID(id);
            carToUpdate.setBrand(car.getBrand());
            carToUpdate.setModel(car.getModel());
            carToUpdate.setElectric(car.isElectric());
            carToUpdate.setAvailableSeats(car.getAvailableSeats());

            carRepository.save(carToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public double electricCarsPercentage() {
        try {
            int carNumber = this.findAll().size();
            int electricCarsNumber = carRepository.findAllElectricCars();
            return (double) (electricCarsNumber * 100)/carNumber;
        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
