package com.practice.store.config.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus httpStatus;
    private CustomExceptionResponse response;
}
