package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "shopping_cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping(path = "/add")
    public void add(UserDto user, ProductDto product) {
        shoppingCartService.add(user, product);
    }

    @PostMapping(path = "/get")
    public ListProductDto get(UserDto user) {
        return shoppingCartService.get(user);
    }

}
