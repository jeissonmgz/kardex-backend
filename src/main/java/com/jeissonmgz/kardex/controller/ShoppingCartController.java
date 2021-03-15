package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public void add(UserDto user, ProductDto product) {
        shoppingCartService.add(user, product);
    }

    public ListProductDto get(UserDto user) {
        return shoppingCartService.get(user);
    }

}
