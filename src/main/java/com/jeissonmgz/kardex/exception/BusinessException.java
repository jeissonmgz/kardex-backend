package com.jeissonmgz.kardex.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

    private final BusinessExceptionMessage businessExceptionMessage;

    public BusinessException (BusinessExceptionMessage businessExceptionMessage) {
        super(businessExceptionMessage.getMessage());
        this.businessExceptionMessage = businessExceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return this.businessExceptionMessage.getHttpStatus();
    }
}
