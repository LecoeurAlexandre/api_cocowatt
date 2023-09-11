package com.example.domain.exception;

public class InvalidIdException extends Exception{
    public InvalidIdException(int id) {
        super("ID : " + id + " invalide");
    }
}
