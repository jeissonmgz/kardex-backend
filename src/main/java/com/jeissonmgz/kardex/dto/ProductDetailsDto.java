package com.jeissonmgz.kardex.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {

    ProductDto product;
    List<ProductDetailDto> details;
}
