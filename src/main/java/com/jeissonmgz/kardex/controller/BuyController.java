package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "buys")
@RequiredArgsConstructor
public class BuyController {

    private final StockService stockService;

    @PostMapping(path = "")
    public void save(@RequestBody ListProductDto listProductDto) {
        stockService.input(listProductDto);
    }
}
