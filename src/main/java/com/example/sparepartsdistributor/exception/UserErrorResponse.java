package com.example.sparepartsdistributor.exception;

public record UserErrorResponse(int status,
                                String message,
                                long timeStamp) {
}
