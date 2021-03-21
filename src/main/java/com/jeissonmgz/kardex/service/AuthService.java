package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.entity.UserEntity;
import com.jeissonmgz.kardex.exception.BusinessException;
import com.jeissonmgz.kardex.exception.BusinessExceptionMessage;
import com.jeissonmgz.kardex.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    @Value("${auth.key.secret}")
    private String secretKey;

    public boolean register(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername()).map(
                userEntity -> false
        ).orElseGet(()->{
                try {
                    userRepository.save(UserEntity.builder()
                            .name(userDto.getName())
                            .mail(userDto.getMail())
                            .username(userDto.getUsername())
                            .password(userDto.getPassword())
                            .build());
                    return true;
                }
                catch (Exception e) {
                    return false;
                }});
    }

    public Either<BusinessException, String> login(UserDto userDto) {
        return (Either<BusinessException, String>) userRepository.findByUsername(userDto.getUsername()).map(
                userEntity -> userEntity.getPassword().equals(userDto.getPassword())
        ).map(isAuth->{
            if(isAuth) {
                return Either.right(getJwt(userDto));
            }
            return Either.left(new BusinessException(BusinessExceptionMessage.LOGIN_FAILED));
        }).orElse(Either.left(new BusinessException(BusinessExceptionMessage.LOGIN_FAILED)));
    }

    public String getJwt(UserDto userDto) {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("HulkStore")
                .setSubject(userDto.getUsername())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm
                                .HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}
