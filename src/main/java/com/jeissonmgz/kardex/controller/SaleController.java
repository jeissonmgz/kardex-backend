package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "sales")
@RequiredArgsConstructor
public class SaleController {

    private final StockService stockService;

    @PostMapping(path = "")
    public boolean save (UserDto user) {
        return stockService.output(user);
    }
}
