package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.entity.UserEntity;
import com.jeissonmgz.kardex.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(authService, "secretKey", "example");
    }

    @Test
    void callRegister_whenExist_thenFalse() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(UserEntity.builder().build()));
        Assertions.assertFalse(authService.register(getUser()));
    }

    @Test
    void callRegister_whenExist_thenTrue() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertTrue(authService.register(getUser()));
    }

    @Test
    void callLogin_whenAllSucces_thenReturnJwt() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(UserEntity.builder().password("123").build()));
        Assertions.assertTrue(authService.login(getUser()).isRight());
    }

    @Test
    void callLogin_whenPasswordInvalid_thenReturnException() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(UserEntity.builder().password("12345").build()));
        Assertions.assertTrue(authService.login(getUser()).isLeft());
    }

    @Test
    void callLogin_whenInvalidUser_thenReturnException() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertTrue(authService.login(getUser()).isLeft());
    }

    private UserDto getUser() {
        return UserDto.builder()
                .id(123)
                .name("John")
                .mail("john@mail.com")
                .password("123")
                .username("jdoe")
                .build();
    }
}