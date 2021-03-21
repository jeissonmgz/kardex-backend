package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Test
    void add() {
        shoppingCartController.add(ProductDto.builder().build(), getAuthentication());
        Mockito.verify(shoppingCartService, Mockito.times(1)).add(Mockito.any(), Mockito.any());
    }

    @Test
    void get() {
        Mockito.when(shoppingCartService.get(Mockito.any())).thenReturn(ListProductDto.builder().idUser(678).build());
        Assertions.assertEquals(678, shoppingCartController.get(getAuthentication()).getIdUser());
    }

    private Authentication getAuthentication() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }
}