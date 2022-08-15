package com.practice.store.config.exception.util;

import com.practice.store.config.exception.model.CustomException;
import com.practice.store.config.exception.model.CustomExceptionResponse;
import com.practice.store.config.exception.type.CustomError;
import org.springframework.http.HttpStatus;

public class ExceptionUtil {

    /*
    유효성 검사 실패 확인후 예외 던지기
     */
    public static void isException400(boolean flag, String message) {
        if (flag) {
            CustomExceptionResponse errorResponse = new CustomExceptionResponse(CustomError.E400, message);
            throw new CustomException(HttpStatus.BAD_REQUEST, errorResponse);
        }
    }

    /*
    인증 실패 확인후 예외 던지기
     */
    public static void isException401(boolean flag, String message) {
        if (flag) {
            CustomExceptionResponse errorResponse = new CustomExceptionResponse(CustomError.E401, message);
            throw new CustomException(HttpStatus.UNAUTHORIZED, errorResponse);
        }
    }

    /*
    리소스 없음 확인후 예외 던지기
     */
    public static void isException404(boolean flag, String message) {
        if (flag) {
            CustomExceptionResponse errorResponse = new CustomExceptionResponse(CustomError.E404, message);
            throw new CustomException(HttpStatus.NOT_FOUND, errorResponse);
        }
    }

    /*
        리소스 없음 확인후 예외 던지기
    */
    public static void isException403(boolean flag, String message) {
        if (flag) {
            CustomExceptionResponse errorResponse = new CustomExceptionResponse(CustomError.E403, message);
            throw new CustomException(HttpStatus.FORBIDDEN, errorResponse);
        }
    }
}
