package com.example.domain.service;

import com.example.domain.entity.Car;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import com.example.domain.exception.*;
import com.example.domain.port.CarRepository;
import com.example.domain.port.UserRepository;
import com.example.domain.port.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public UserServiceImpl(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public User createUser(String firstName, String lastName, String phone, String email, String password, boolean isAdmin, String imageUrl) throws EmptyParameterException, InvalidEmailException, InvalidPhoneException, EmailAlreadyExistsException, PhoneAlreadyExistsException {
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new EmptyParameterException();
        }
        //Si imageUrl est vide ou n'est pas une URL on assigne une image par défaut
        if (imageUrl.isEmpty() || !Pattern.compile("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$").matcher(imageUrl).matches()) {
            imageUrl = "https://isobarscience-1bfd8.kxcdn.com/wp-content/uploads/2020/09/default-profile-picture1.jpg";
        }
        //Vérifie si l'email est valide
        if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
            throw new InvalidEmailException();
        }
        //Vérifie si le téléphone est valide
        if (!Pattern.compile("^0[1-9](?:\\d{2}){4}$").matcher(phone).matches()) {
            throw new InvalidPhoneException();
        }
        //Vérifie si le téléphone ou l'email est déjà utilisé
        for (User u : userRepository.findAll()) {
            if (u.getPhone().equals(phone)) {
                throw new PhoneAlreadyExistsException();
            }
            if (u.getEmail().equals(email)) {
                throw new EmailAlreadyExistsException();
            }
        }
        try {
            List<Trip> tripList = new ArrayList<>();
            List<Reservation> reservationList = new ArrayList<>();
            Car car = null;
            User user = new User(firstName, lastName, phone, email, password, tripList, reservationList, isAdmin, imageUrl, car);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findById(int id) throws InvalidIdException, EntityNotFoundException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Utilisateur");
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, User user) throws InvalidIdException, EntityNotFoundException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }

        try {
            User userToUpdate = userRepository.findById(id);
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setAdmin(user.isAdmin());
            userToUpdate.setReservationList(user.getReservationList());
            userToUpdate.setTripList(user.getTripList());
            userToUpdate.setImageUrl(user.getImageUrl());
            userRepository.save(userToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws InvalidIdException, EntityNotFoundException {
        if (id <= 0) {
            throw new InvalidIdException(id);
        }

       try {
           User user = userRepository.findById(id);
           userRepository.delete(user);
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        try {
            return userRepository.findByLastName(lastName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addUserToCar(int userId, int carId) throws InvalidIdException {
        if (userId <= 0) {
            throw new InvalidIdException(userId);
        }
        if (carId <= 0) {
            throw new InvalidIdException(carId);
        }
        try {
            Car car = carRepository.findByID(carId);
            User user = userRepository.findById(userId);
            user.setCar(car);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EmptyParameterException {
        if (email.isEmpty() || password.isEmpty()) {
            throw new EmptyParameterException();
        }
        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) throws EmptyParameterException {
        if (email.isEmpty()) {
            throw new EmptyParameterException();
        }
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
