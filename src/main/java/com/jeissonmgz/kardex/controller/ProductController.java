package com.jeissonmgz.kardex.controller;

import com.jeissonmgz.kardex.dto.ProductDetailDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "")
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductDetailDto getProductDetail(@PathVariable("id")  Integer productId){
        return productService.getProductDetail(ProductDto.builder().id(productId).build());
    }

    @PostMapping(path = "")
    public ProductDto save(ProductDto productDto) {
        return productService.save(productDto);
    }
}
