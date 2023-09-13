package com.example.adapters.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatDTO {
    private int drivers;
    private int travellers;
    private double electricPercentage;
    private int doneTrips;
    private int notDoneTrips;
    private double km;
    private int books;
}
