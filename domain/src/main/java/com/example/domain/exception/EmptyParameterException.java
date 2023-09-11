package com.example.domain.exception;

public class EmptyParameterException extends Exception  {
    public EmptyParameterException() {
        super("Les chaînes de caractère de doivent pas être vide");
    }
}
