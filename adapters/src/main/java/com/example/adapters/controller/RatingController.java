package com.example.adapters.controller;

import com.example.adapters.entity.RatingDtoRequest;
import com.example.domain.entity.Rating;
import com.example.domain.port.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rating")
@CrossOrigin("*")
public class RatingController {

    private final RatingService ratingService;
    private final ModelMapper modelMapper;

    public RatingController(RatingService ratingService, ModelMapper modelMapper) {
        this.ratingService = ratingService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Création d'avis", description = "Permet de créer un objet Rating")
    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody RatingDtoRequest ratingDtoRequest) {
        try {
            ratingService.createRating(
                    ratingDtoRequest.getValue(),
                    ratingDtoRequest.getComment(),
                    ratingDtoRequest.getUserId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Avis ajouté");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Récupère les avis", description = "Permet de récupèrer une liste avec tout les objets Rating")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Rating> ratingList = ratingService.getAll();
            return ResponseEntity.ok(ratingList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Récupère avis par Id", description = "Permet de récupèrer un objet Rating ayant pour Id celui indiqué dans le chemin")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserRatings(@PathVariable int id) {
        try {
            List<Rating> ratingList = ratingService.getAllByUserId(id);
            return ResponseEntity.ok(ratingList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Suppression d'avis", description = "Permet de supprimer un objet Rating ayant pour Id celui indiqué dans le chemin")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            ratingService.delete(id);
            return ResponseEntity.ok("Avis supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mise à jour avis", description = "Permet de mettre à jour un objet Rating ayant pour Id celui indiqué dans le chemin, en récupérant les attributs de l'objet dans le corps de la requête")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RatingDtoRequest ratingDtoRequest) {
        try {
            ratingService.update(id, modelMapper.map(ratingDtoRequest, Rating.class));
            return ResponseEntity.ok("Avis mis à jour");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
