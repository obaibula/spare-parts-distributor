package com.example.sparepartsdistributor.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class UserExceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> userNotFoundHandler(UserNotFoundException e){
        String message = e.getMessage();
        var response = new UserErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                System.currentTimeMillis());

        log.error(message);
        e.printStackTrace();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
