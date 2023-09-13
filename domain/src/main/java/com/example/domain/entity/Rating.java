package com.example.domain.entity;


import java.time.LocalDate;

public class Rating {
    private String id;
    private int value;
    private String comment;
    private LocalDate date;
    private int userId;

    public Rating() {
    }

    public Rating(int value, String comment, LocalDate date, int userId) {
        this.value = value;
        this.comment = comment;
        this.date = date;
        this.userId = userId;
    }

    public Rating(String id, int value, String comment, LocalDate date, int userId) {
        this.id = id;
        this.value = value;
        this.comment = comment;
        this.date = date;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
