package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "")
    public boolean register(UserDto userDto) {
        return authService.register(userDto);
    }

    @PostMapping(path = "/login")
    public String login(UserDto userDto) {
        return authService.login(userDto);
    }
}
