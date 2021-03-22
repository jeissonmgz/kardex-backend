package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.ShoppingCartEntity;
import com.jeissonmgz.kardex.entity.ShoppingCartProductEntity;
import com.jeissonmgz.kardex.entity.UserEntity;
import com.jeissonmgz.kardex.repository.ShoppingCartProductRepository;
import com.jeissonmgz.kardex.repository.ShoppingCartRepository;
import com.jeissonmgz.kardex.repository.UserRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShoppingCartProductRepository shoppingCartProductRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void callAdd_whenNoExistShoppingCar() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(ShoppingCartEntity.builder().products(new ArrayList<>()).build());
        Mockito.when(shoppingCartRepository.findByUser(Mockito.any())).thenReturn(Optional.empty());
        shoppingCartService.add("123", ProductDto.builder().quantity(1).build());
        Mockito.verify(shoppingCartProductRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void callAdd_whenExistShoppingCar() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(shoppingCartRepository.findByUser(Mockito.any())).thenReturn(Optional.of(
                ShoppingCartEntity.builder()
                        .products(List.of(ShoppingCartProductEntity.builder()
                                .productEntity(ProductEntity.builder().id(1233).build())
                                .build()).asJava())
                        .build()));
        shoppingCartService.add("123", ProductDto.builder().quantity(1).build());
        Mockito.verify(shoppingCartProductRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void get() {
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(UserEntity.builder().id(9).build()));
        Mockito.when(shoppingCartRepository.findByUser(Mockito.any())).thenReturn(Optional.of(
                ShoppingCartEntity.builder()
                        .products(List.of(ShoppingCartProductEntity.builder()
                                .productEntity(ProductEntity.builder().id(1233).build())
                                .build()).asJava())
                        .build()));
        Assertions.assertEquals(9, shoppingCartService.get("1").getIdUser());
    }
}