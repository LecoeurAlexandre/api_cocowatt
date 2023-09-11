package com.example.domain.entity;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int availableSeats;
    private boolean isElectric;

    public Car() {
    }

    public Car(String brand, String model, int availableSeats, boolean isElectric) {
        this.brand = brand;
        this.model = model;
        this.availableSeats = availableSeats;
        this.isElectric = isElectric;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

}
