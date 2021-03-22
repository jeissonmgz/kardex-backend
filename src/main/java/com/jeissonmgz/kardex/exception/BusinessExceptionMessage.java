package com.jeissonmgz.kardex.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum BusinessExceptionMessage {

    USER_REGISTERED("Usuario ya registrado", HttpStatus.CONFLICT),
    LOGIN_FAILED("Credenciales incorecctas", HttpStatus.UNAUTHORIZED),
    NO_PRODUCTS("Producto sin stock", HttpStatus.CONFLICT),
    PRODUCT_NOT_FOUND("Producto no existe", HttpStatus.NOT_FOUND),
    LOGIN_NEEDED("Usuario no autenticado", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

}
