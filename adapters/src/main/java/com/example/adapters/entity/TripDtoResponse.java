package com.example.adapters.entity;

import com.example.domain.entity.Reservation;
import com.example.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDtoResponse {
    private int id;
    private String startingPoint;
    private String endPoint;
    private LocalDate localDate;
    private LocalTime localTime;
    private int availableSeats;
    private int distance;
    private User user;
    private List<Reservation> reservationList;
}
