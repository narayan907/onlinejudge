package dev.narayan.onlinejudge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice()
public class RestExceptionHandler {
    @ExceptionHandler(UserAlreadyRegistered.class)
    protected ResponseEntity<Object> handleUserAlreadyRegistered(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }
}