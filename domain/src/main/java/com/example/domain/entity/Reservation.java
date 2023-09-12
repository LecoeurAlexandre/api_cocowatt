package com.example.domain.entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private User user;
    private Trip trip;
    private Car car;
    private LocalDate date;

    public Reservation() {
    }

    public Reservation(User user, Trip trip, Car car, LocalDate date) {
        this.user = user;
        this.trip = trip;
        this.car = car;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
