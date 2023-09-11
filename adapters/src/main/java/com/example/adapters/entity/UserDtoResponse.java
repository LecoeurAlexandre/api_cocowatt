package com.example.adapters.entity;

import com.example.domain.entity.Car;
import com.example.domain.entity.Reservation;
import com.example.domain.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private List<Trip> tripList;
    private List<Reservation> reservationList;
    private boolean isAdmin;
    private String imageUrl;
    private Car car;
}
