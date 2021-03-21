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
    LOGIN_NEEDED("Usuario no autenticado", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

}
