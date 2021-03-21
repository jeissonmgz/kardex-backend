package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.service.AuthService;
import com.jeissonmgz.kardex.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class BuyControllerTest {


    @InjectMocks()
    private BuyController buyController;
    @Mock
    private StockService stockService;

    @Test
    void happyPath() {
        buyController.save(ListProductDto.builder().build());
        Mockito.verify(stockService, Mockito.times(1)).input(Mockito.any());
    }

}