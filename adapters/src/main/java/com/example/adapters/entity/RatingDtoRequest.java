package com.example.adapters.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDtoRequest {
    private int value;
    private String comment;
    private int userId;
}
