package com.example.domain.exception;

public class InvalidSeatsException extends Exception{
    public InvalidSeatsException() {
        super("Il doit y avoir au moins une place disponible");
    }
}
