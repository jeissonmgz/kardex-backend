package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public boolean register(@RequestBody UserDto userDto) {
        return authService.register(userDto);
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody UserDto userDto) throws Exception {
        return authService.login(userDto)
                .getOrElseThrow((exception)->exception);
    }
}
