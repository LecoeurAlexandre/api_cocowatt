package com.example.domain.exception;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException() {
        super("Adresse mail déjà utilisée");
    }
}
