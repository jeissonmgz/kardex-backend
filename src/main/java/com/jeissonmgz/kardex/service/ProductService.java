package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ProductDetailDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getProducts(){
        return productRepository.findAll().stream().map(productEntity ->
                        ProductDto.builder()
                                .id(productEntity.getId())
                                .name(productEntity.getName())
                                .price(productEntity.getPrice())
                                .build()
                ).collect(Collectors.toList());
    }

    public ProductDetailDto getProductDetail(ProductDto productDto){
        return null;
    }

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .max(productDto.getMax())
                .min(productDto.getMin())
                .price(productDto.getPrice())
                .build());
        productDto.setId(productEntity.getId());
        return productDto;
    }
}
