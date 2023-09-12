package com.example.adapters.entity;

import com.example.domain.entity.Car;
import com.example.domain.entity.Trip;
import com.example.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDtoResponse {
    private int id;
    private User user;
    private Trip trip;
    private Car car;
    private LocalDate date;
}
