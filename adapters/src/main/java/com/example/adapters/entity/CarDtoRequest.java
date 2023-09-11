package com.example.adapters.entity;

import com.example.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDtoRequest {
    private String brand;
    private String model;
    private int availableSeats;
    private boolean isElectric;
}
