package com.example.domain.service;

import com.example.domain.entity.Rating;
import com.example.domain.entity.User;
import com.example.domain.exception.EmptyParameterException;
import com.example.domain.exception.InvalidIdException;
import com.example.domain.exception.InvalidValueException;
import com.example.domain.port.RatingRepository;
import com.example.domain.port.RatingService;
import com.example.domain.port.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRating(int value, String comment, int userId) throws EmptyParameterException, InvalidValueException {
        if (comment.isEmpty()) {
            throw new EmptyParameterException();
        }
        if (value <= 0) {
            throw new InvalidValueException();
        }
        try {
            User user = userRepository.findById(userId);
            if (user != null) {
                LocalDate date = LocalDate.now();
                Rating rating = new Rating(value, comment, date, userId);
                ratingRepository.save(rating);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Rating getById(String id) throws InvalidIdException {
        if (id.isEmpty()) {
            throw new InvalidIdException(Integer.parseInt(id));
        }
        try {
            return ratingRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Rating> getAll() {
        try {
            return ratingRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Rating> getAllByUserId(int id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            return ratingRepository.findAllByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws InvalidIdException {
        if (id.isEmpty()) {
            throw new InvalidIdException(Integer.parseInt(id));
        }
        try {
            Rating rating = ratingRepository.findById(id);
            ratingRepository.delete(rating);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(String id, Rating rating) throws InvalidIdException {
        if (id.isEmpty()) {
            throw new InvalidIdException(Integer.parseInt(id));
        }
        try {
            Rating ratingToUpdate = ratingRepository.findById(id);
            ratingToUpdate.setValue(rating.getValue());
            ratingToUpdate.setComment(rating.getComment());

            ratingRepository.save(ratingToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
