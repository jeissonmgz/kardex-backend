package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.entity.UserEntity;
import com.jeissonmgz.kardex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public boolean register(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());
        if (userEntity == null) {
            return false;
        }
        userRepository.save(UserEntity.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build());
        return true;
    }

    public String login(UserDto userDto) {
        return null;
    }
}
