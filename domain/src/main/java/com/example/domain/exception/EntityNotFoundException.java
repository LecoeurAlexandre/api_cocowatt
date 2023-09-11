package com.example.domain.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String entity) {
        super(entity + " introuvable");
    }
}
