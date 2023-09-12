package com.example.infrastructure.repository.impl;

import com.example.domain.entity.Trip;
import com.example.domain.port.TripRepository;
import com.example.infrastructure.entity.TripEntity;
import com.example.infrastructure.repository.TripEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepositoryImpl implements TripRepository {

    private final ModelMapper modelMapper;
    private final TripEntityRepository tripEntityRepository;

    public TripRepositoryImpl(ModelMapper modelMapper, TripEntityRepository tripEntityRepository) {
        this.modelMapper = modelMapper;
        this.tripEntityRepository = tripEntityRepository;
    }

    @Override
    public void save(Trip trip) {
        TripEntity tripEntity = modelMapper.map(trip, TripEntity.class);
        tripEntityRepository.save(tripEntity);
    }

    @Override
    public Trip findById(int id) {
        TripEntity tripEntity = tripEntityRepository.findById(id).get();
        return modelMapper.map(tripEntity, Trip.class);
    }

    @Override
    public List<Trip> findAll() {
        List<TripEntity> tripEntityList = (List<TripEntity>) tripEntityRepository.findAll();
        List<Trip> tripList = new ArrayList<>();
        for (TripEntity t : tripEntityList) {
            Trip trip = modelMapper.map(t, Trip.class);
            tripList.add(trip);
        }
        return tripList;
    }

    @Override
    public void delete(Trip trip) {
        TripEntity tripEntity = modelMapper.map(trip, TripEntity.class);
        tripEntityRepository.delete(tripEntity);
    }
}
