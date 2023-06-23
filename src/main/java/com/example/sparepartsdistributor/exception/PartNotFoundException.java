package com.example.sparepartsdistributor.exception;

public class PartNotFoundException extends EntityNotFoundException {
    public PartNotFoundException(String message) {
        super(message);
    }
}
