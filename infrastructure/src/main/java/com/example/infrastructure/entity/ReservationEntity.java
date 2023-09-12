package com.example.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    //@JoinColumn(nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    //@JoinColumn(nullable = false)
    private TripEntity trip;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    //@JoinColumn(nullable = false)
    private CarEntity car;
    private LocalDate date;
}
