package com.example.adapters.controller;

import com.example.adapters.entity.ReservationDtoRequest;
import com.example.domain.port.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {
    private final ModelMapper modelMapper;
    private final ReservationService reservationService;

    public ReservationController(ModelMapper modelMapper, ReservationService reservationService) {
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }


    @PostMapping("")
    public ResponseEntity post(@RequestBody ReservationDtoRequest reservationDtoRequest) {

    }
}
