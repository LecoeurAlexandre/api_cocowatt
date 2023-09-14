package com.example.infrastructure.repository.impl;

import com.example.domain.entity.Car;
import com.example.domain.port.CarRepository;
import com.example.infrastructure.entity.CarEntity;
import com.example.infrastructure.repository.CarEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final ModelMapper modelMapper;
    private final CarEntityRepository carEntityRepository;

    public CarRepositoryImpl(ModelMapper modelMapper, CarEntityRepository carEntityRepository) {
        this.modelMapper = modelMapper;
        this.carEntityRepository = carEntityRepository;
    }

    @Override
    public void save(Car car) {
        CarEntity carEntity = modelMapper.map(car, CarEntity.class);
        carEntityRepository.save(carEntity);
    }

    @Override
    public Car findByID(int id) {
        CarEntity carEntity = carEntityRepository.findById(id).get();
        Car car = modelMapper.map(carEntity, Car.class);
        return car;
    }

    @Override
    public List<Car> findAll() {
        List<CarEntity> carEntities = (List<CarEntity>) carEntityRepository.findAll();
        List<Car> carList = new ArrayList<>();
        for (CarEntity c : carEntities) {
            Car car = modelMapper.map(c, Car.class);
            carList.add(car);
        }
        return carList;
    }

    @Override
    public void delete(Car car) {
        CarEntity carEntity = modelMapper.map(car, CarEntity.class);
        carEntityRepository.delete(carEntity);
    }

    @Override
    public int findAllElectricCars() {
        return carEntityRepository.getCarCount();
    }
}
