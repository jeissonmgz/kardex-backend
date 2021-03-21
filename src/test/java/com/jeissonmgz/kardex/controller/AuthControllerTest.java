package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.exception.BusinessException;
import com.jeissonmgz.kardex.exception.BusinessExceptionMessage;
import com.jeissonmgz.kardex.service.AuthService;
import io.vavr.control.Either;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks()
    private AuthController authController;
    @Mock
    private AuthService authService;

    @Test
    void callRegister_whenSuccesful_thenTrue() {
        when(authService.register(any())).thenReturn(true);
        Assertions.assertTrue(authController.register(new UserDto()));
    }

    @Test
    void callRegister_whenError_thenFalse() {
        when(authService.register(any())).thenReturn(false);
        Assertions.assertFalse(authController.register(new UserDto()));
    }

    @Test
    void callLogin_whenSuccesful_thenFinish() throws BusinessException {
        when(authService.login(any())).thenReturn(Either.right(""));
        authController.login(new UserDto());
    }

    @Test()
    void callLogin_whenException_thenReturnException() {
        when(authService.login(any())).thenReturn(Either.left(new BusinessException(BusinessExceptionMessage.LOGIN_FAILED)));
        Assertions.assertThrows(BusinessException.class, () -> authController.login(new UserDto()));
    }
}