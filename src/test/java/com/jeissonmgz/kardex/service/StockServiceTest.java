package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.StockEntity;
import com.jeissonmgz.kardex.repository.StockRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;
    @Mock
    private ShoppingCartService shoppingCartService;

    @Test
    void callInput() {
        Mockito.when(stockRepository.findByProduct(Mockito.any())).thenReturn(
          List.of(StockEntity.builder().quantity(1).value(BigDecimal.ONE).isInput(true).build()).asJava()
        );
        stockService.input(getListProductDto());
    }

    @Test
    void callInput_whenEmpty() {
        Mockito.when(stockRepository.findByProduct(Mockito.any())).thenReturn(
                List.of(StockEntity.builder().quantity(1).build()).asJava()
        );
        stockService.input(getListProductDto());
    }

    @Test
    void callOutput_whenNoStock() {
        Mockito.when(shoppingCartService.get(Mockito.any())).thenReturn(
                getListProductDto()
        );
        assertFalse(stockService.output("user"));
    }

    @Test
    void callOutput() {
        Mockito.when(shoppingCartService.get(Mockito.any())).thenReturn(
                getListProductDto()
        );
        Mockito.when(stockRepository.findByProduct(Mockito.any())).thenReturn(
                List.of(StockEntity.builder().quantity(1).value(BigDecimal.ONE).isInput(true).build()).asJava()
        );
        assertTrue(stockService.output("user"));
    }

    private ListProductDto getListProductDto() {
        return ListProductDto.builder()
                .idUser(1)
                .products(
                        List.of(ProductDto.builder()
                                .quantity(1)
                                .id(2)
                                .price(2000)
                                .name("Vaso")
                                .max(100)
                                .min(2)
                                .build()
                        ).asJava()
                )
                .build();
    }

}