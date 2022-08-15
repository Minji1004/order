package com.practice.store.config.exception.handler;

import com.practice.store.config.exception.model.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler{
    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException e) {
        return new ResponseEntity(e.getResponse(), e.getHttpStatus());
    }
}
