package com.example.adapters.controller;

import com.example.adapters.entity.UserDtoRequest;
import com.example.adapters.entity.UserDtoResponse;
import com.example.adapters.entity.UserManagementResponseDTO;
import com.example.adapters.entity.UsersListDTO;
import com.example.domain.entity.User;

import com.example.domain.port.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    // Cette méthode n'est plus censée être appelée, la création d'utilisateur se fait
    // via la méthode register dans AuthController

//    @PostMapping("/create")
//    public ResponseEntity<?> post(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone, @RequestParam String email, @RequestParam String password, @RequestParam boolean isAdmin, @RequestParam String imageUrl) {
//        try {
//            userService.createUser(firstName, lastName, phone, email, password, isAdmin, imageUrl);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé");
//        } catch (Exception e ) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }


    @Operation(summary = "Récupère les utilisateurs", description = "Permet de récupèrer une liste avec tout les objets User")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<UserDtoResponse> userDtoResponseList = new ArrayList<>();
        for (User u : userService.findAll()) {
            UserDtoResponse userDtoResponse = modelMapper.map(u, UserDtoResponse.class);
            userDtoResponseList.add(userDtoResponse);
        }
        return ResponseEntity.ok(userDtoResponseList);
    }

    @Operation(summary = "Récupère utilisateur par Id", description = "Permet de récupèrer un objet User ayant pour Id celui indiqué dans le chemin")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            UserDtoResponse userDtoResponse = modelMapper.map(userService.findById(id), UserDtoResponse.class);
            return ResponseEntity.ok(userDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getByIdAdmin(@PathVariable int id) {
        try {
            UserManagementResponseDTO userDtoResponse = modelMapper.map(userService.findById(id), UserManagementResponseDTO.class);
            return ResponseEntity.ok(userDtoResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Récupère utilisateur par nom de famille", description = "Permet de récupérer un objet User ayant pour lastName celui indiqué dans le chemin")
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<?> getAllByLastName(@PathVariable String lastName) {
        try {
            UsersListDTO usersListDTO = new UsersListDTO();
            List<UserManagementResponseDTO> userDtoResponseList = new ArrayList<>();
            for (User u : userService.findAllByLastName(lastName)) {
                UserManagementResponseDTO userManagementResponseDTO = modelMapper.map(u, UserManagementResponseDTO.class);
                userDtoResponseList.add(userManagementResponseDTO);
            }
            usersListDTO.setList(userDtoResponseList);
            return ResponseEntity.ok(usersListDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mise à jour utilisateur", description = "Permet de mettre à jour un objet User")
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

    @Operation(summary = "Suppression utilisateur", description = "Permet de supprimer un objet User via son Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Utilisateur supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Associer utilisateur à une voiture", description = "Permet d'associer un objet User à un objet Car via leur Id")
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
