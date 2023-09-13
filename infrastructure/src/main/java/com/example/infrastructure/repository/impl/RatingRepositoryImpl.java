package com.example.infrastructure.repository.impl;

import com.example.domain.entity.Rating;
import com.example.domain.port.RatingRepository;
import com.example.infrastructure.entity.RatingEntity;
import com.example.infrastructure.repository.RatingEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

    private final ModelMapper modelMapper;
    private final RatingEntityRepository repository;

    public RatingRepositoryImpl(ModelMapper modelMapper, RatingEntityRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public void save(Rating rating) {
        RatingEntity ratingEntity = modelMapper.map(rating, RatingEntity.class);
        repository.save(ratingEntity);
    }

    @Override
    public Rating findById(String id) {
        RatingEntity ratingEntity = repository.findById(id).get();
        return modelMapper.map(ratingEntity, Rating.class);
    }

    @Override
    public List<Rating> findAll() {
        List<RatingEntity> ratingEntitieList = repository.findAll();
        List<Rating> ratingList = new ArrayList<>();
        for (RatingEntity r : ratingEntitieList) {
            Rating rating = modelMapper.map(r, Rating.class);
            ratingList.add(rating);
        }
        return ratingList;
    }

    @Override
    public void delete(Rating rating) {
        RatingEntity ratingEntity = modelMapper.map(rating, RatingEntity.class);
        repository.delete(ratingEntity);
    }

    @Override
    public List<Rating> findAllByUserId(int userId) {
        List<RatingEntity> ratingEntityList = repository.findAllByUserId(userId);
        List<Rating> ratingList = new ArrayList<>();
        for (RatingEntity r : ratingEntityList) {
            Rating rating = modelMapper.map(r, Rating.class);
            ratingList.add(rating);
        }
        return ratingList;
    }
}
