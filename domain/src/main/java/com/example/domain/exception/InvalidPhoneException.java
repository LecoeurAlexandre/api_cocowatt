package com.example.domain.exception;

public class InvalidPhoneException extends Exception{
    public InvalidPhoneException() {
        super("Veuillez saisir un numéro de téléphone valide");
    }
}
