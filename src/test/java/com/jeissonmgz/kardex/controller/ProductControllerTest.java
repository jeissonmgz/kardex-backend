package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ProductDetailDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.EConcept;
import com.jeissonmgz.kardex.service.ProductService;
import io.vavr.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks()
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void getProducts() {
        Mockito.when(productService.getProducts()).thenReturn((List.of(ProductDto.builder().build()).asJava()));
        Assertions.assertEquals(1, productController.getProducts().size());
    }

    @Test
    void getProductDetail() {
        Mockito.when(productService.getProductDetail(Mockito.any())).thenReturn(ProductDetailDto.builder().concept(EConcept.BUY).build());
        Assertions.assertEquals(EConcept.BUY, productController.getProductDetail(1).getConcept());
    }

    @Test
    void save() {
        Mockito.when(productService.save(Mockito.any())).thenReturn(ProductDto.builder().price(5000).build());
        Assertions.assertEquals(5000, productController.save(ProductDto.builder().build()).getPrice());
    }
}