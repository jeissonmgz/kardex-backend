package com.jeissonmgz.kardex.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<String> bussinessErrorHandler(BusinessException businessException) {
        return new ResponseEntity<String>(businessException.getMessage(), businessException.getHttpStatus());
    }
}
