package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.EConcept;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.StockEntity;
import com.jeissonmgz.kardex.exception.BusinessException;
import com.jeissonmgz.kardex.repository.ProductRepository;
import com.jeissonmgz.kardex.repository.StockRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private StockRepository stockRepository;

    @Test
    void getProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(getProductEntity()).asJava());
        Assertions.assertEquals(1, productService.getProducts().size());
    }

    @Test
    void getProductDetail() throws BusinessException {
        Mockito.when(stockRepository.findByProduct(Mockito.any())).thenReturn(List.of(StockEntity.builder()
                .id(1)
                .valueTotal(BigDecimal.valueOf(100))
                .quantityTotal(1)
                .value(BigDecimal.valueOf(100))
                .quantity(1)
                .isInput(true)
                .date(new Date())
                .concept(EConcept.BUY)
                .product(ProductEntity.builder().build())
                .build()).asJava());
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(getProductEntity()));
        Assertions.assertNotNull(productService.getProductDetail(ProductDto.builder().build()));
    }

    @Test
    void save() {
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(getProductEntity());
        Assertions.assertEquals(12, productService.save(ProductDto.builder().build()).getId());
    }

    private ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .id(12)
                .min(5)
                .max(20)
                .name("Vaso")
                .price(29000)
                .build();
    }
}