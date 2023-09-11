package com.example.domain.exception;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("Veuillez saisir une adresse mail valide");
    }
}
