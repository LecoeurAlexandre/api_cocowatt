package com.example.domain.port;

import com.example.domain.entity.Rating;
import com.example.domain.exception.EmptyParameterException;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.exception.InvalidValueException;

import java.util.List;

public interface RatingService {

    void createRating(int value, String comment, int userId) throws EmptyParameterException, InvalidValueException, InvalidIdException;
    Rating getById(String id) throws InvalidIdException, EmptyParameterException;
    List<Rating> getAll();
    List<Rating> getAllByUserId(int id) throws InvalidIdException;
    void delete(String id) throws InvalidIdException, EmptyParameterException;
    void update(String id, Rating rating) throws InvalidIdException, EmptyParameterException;
}
