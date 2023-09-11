package com.example.domain.exception;

public class InvalidSeatNumberException extends Exception{
    public InvalidSeatNumberException() {
        super("Il doit y avoir au moins 1 place disponible");
    }
}
