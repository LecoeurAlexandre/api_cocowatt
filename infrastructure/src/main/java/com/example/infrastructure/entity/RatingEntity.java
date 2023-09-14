package com.example.infrastructure.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("rating")
public class RatingEntity {
    @Id
    private String id;
    private int value;
    private String comment;
    private LocalDate date;
    private int userId;
}
