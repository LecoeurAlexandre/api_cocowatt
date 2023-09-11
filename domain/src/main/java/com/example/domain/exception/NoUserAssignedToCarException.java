package com.example.domain.exception;

public class NoUserAssignedToCarException extends Exception{
    public NoUserAssignedToCarException() {
        super("Aucun utilisateur n'est associé à la voiture");
    }
}
