package com.practice.store.config.exception.model;

import com.practice.store.config.exception.type.CustomError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomExceptionResponse {
    private String errorCode;
    private String message;
    private String remark;

    public CustomExceptionResponse(CustomError customError) {
        this.errorCode = customError.toString();
        this.message = customError.getMessage();
    }

    public CustomExceptionResponse(CustomError customError, String remark) {
        this(customError);
        this.remark = remark;
    }
}
