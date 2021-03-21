package com.jeissonmgz.kardex.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalControllerExceptionHandlerTest {

    @InjectMocks
    private GlobalControllerExceptionHandler globalControllerExceptionHandler;

    @Test
    public void bussinessErrorHandler() {
        Assertions.assertEquals(BusinessExceptionMessage.LOGIN_NEEDED.getHttpStatus(),
            globalControllerExceptionHandler
                    .bussinessErrorHandler(new BusinessException(BusinessExceptionMessage.LOGIN_NEEDED))
                .getStatusCode()
        );
    }
}