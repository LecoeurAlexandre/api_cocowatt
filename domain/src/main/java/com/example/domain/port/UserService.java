package com.example.domain.port;

import com.example.domain.entity.Car;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import com.example.domain.exception.*;

import java.util.List;

public interface UserService {
    User createUser(String firstName, String lastName, String phone, String email, String password, boolean isAdmin, String imageUrl) throws EmptyParameterException, InvalidEmailException, InvalidPhoneException, EmailAlreadyExistsException, PhoneAlreadyExistsException;
    User findById(int id) throws InvalidIdException, EntityNotFoundException;
    List<User> findAll();
    void update(int id, User user) throws InvalidIdException, EntityNotFoundException;
    void delete(int id) throws InvalidIdException, EntityNotFoundException;
    List<User> findAllByLastName(String lastName);
    void addUserToCar(int userId, int carId) throws InvalidIdException;
    User findByEmailAndPassword(String email, String password) throws EmptyParameterException;
    User findByEmail(String email) throws EmptyParameterException;
}
