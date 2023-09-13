package com.example.domain.exception;

public class InvalidValueException extends Exception{
    public InvalidValueException() {
        super("La note doit être supérieure à 0");
    }
}
