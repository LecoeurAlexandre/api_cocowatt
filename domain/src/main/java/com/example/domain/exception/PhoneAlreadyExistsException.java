package com.example.domain.exception;

public class PhoneAlreadyExistsException extends Exception{
    public PhoneAlreadyExistsException() {
        super("Numéro de téléphone déjà utilisé");
    }
}
