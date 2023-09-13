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
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    private boolean isAdmin;
    private String imageUrl;
}
