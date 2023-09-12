package com.example.adapters.controller;

import com.example.adapters.entity.UserDtoRequest;
import com.example.adapters.entity.UserDtoResponse;
import com.example.domain.entity.User;

import com.example.domain.port.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<?> post(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone, @RequestParam String email, @RequestParam String password, @RequestParam boolean isAdmin, @RequestParam String imageUrl) {
        try {
            userService.createUser(firstName, lastName, phone, email, password, isAdmin, imageUrl);
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé");
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<UserDtoResponse> userDtoResponseList = new ArrayList<>();
        for (User u : userService.findAll()) {
            UserDtoResponse userDtoResponse = modelMapper.map(u, UserDtoResponse.class);
            userDtoResponseList.add(userDtoResponse);
        }
        return ResponseEntity.ok(userDtoResponseList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            UserDtoResponse userDtoResponse = modelMapper.map(userService.findById(id), UserDtoResponse.class);
            return ResponseEntity.ok(userDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<?> getAllByLastName(@PathVariable String lastName) {
        try {
            List<UserDtoResponse> userDtoResponseList = new ArrayList<>();
            for (User u : userService.findAllByLastName(lastName)) {
                UserDtoResponse userDtoResponse = modelMapper.map(u, UserDtoResponse.class);
                userDtoResponseList.add(userDtoResponse);
            }
            return ResponseEntity.ok(userDtoResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody UserDtoRequest userDtoRequest) {
        try {
            User user = modelMapper.map(userDtoRequest, User.class);
            userService.update(id, user);
            return ResponseEntity.ok("Utilisateur mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Utilisateur supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/addToCar/{carId}")
    public ResponseEntity<?> addUserToCar(@PathVariable int userId, @PathVariable int carId) {
        try {
            userService.addUserToCar(userId, carId);
            return ResponseEntity.ok("Voiture ajoutée à l'utilisateur");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
