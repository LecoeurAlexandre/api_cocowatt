package com.example.adapters.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDtoResponse {
    private String id;
    private int value;
    private String comment;
    private LocalDate date;
    private int userId;
}
