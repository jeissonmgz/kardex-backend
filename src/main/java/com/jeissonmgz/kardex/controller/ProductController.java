package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ProductDetailDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    public ProductDetailDto getProductDetail(ProductDto productDto){
        return productService.getProductDetail(productDto);
    }

    public ProductDto save(ProductDto productDto) {
        return productService.save(productDto);
    }
}
