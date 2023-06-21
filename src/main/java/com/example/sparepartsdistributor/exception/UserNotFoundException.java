package com.example.sparepartsdistributor.exception;


/**
 * Exception thrown when a user entity is not found.
 */
public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
