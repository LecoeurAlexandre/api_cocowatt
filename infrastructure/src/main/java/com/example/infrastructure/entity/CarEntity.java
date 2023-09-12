package com.example.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false)
    private String brand;
    @Column(nullable=false)
    private String model;
    @Column(nullable=false)
    @Min(value = 1, message = "Il doit y avoir au moins une place disponible")
    private int availableSeats;
    @Column(nullable=false)
    private boolean isElectric;
}
