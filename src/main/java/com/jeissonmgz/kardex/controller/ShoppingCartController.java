package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "shopping_cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping(path = "/add")
    public void add(@RequestBody ProductDto product, Authentication authentication) {
        shoppingCartService.add(authentication.getName(), product);
    }

    @PostMapping(path = "/get")
    public ListProductDto get(Authentication authentication) {
        return shoppingCartService.get(authentication.getName());
    }

}
