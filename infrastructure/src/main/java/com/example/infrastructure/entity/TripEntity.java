package com.example.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="starting_point", nullable=false)
    private String startingPoint;
    @Column(name="end_point", nullable=false)
    private String endPoint;
    @Column(nullable=false)
    private LocalDate localDate;
    @Column(nullable=false)
    private LocalTime localTime;
    @Column(name = "available_seats", nullable = false)
    private int availableSeats;
    @Column(name="distance", nullable=false)
    private int distance;
    @ManyToOne
    private UserEntity user;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ReservationEntity> reservationEntityList = new ArrayList<>();

}
