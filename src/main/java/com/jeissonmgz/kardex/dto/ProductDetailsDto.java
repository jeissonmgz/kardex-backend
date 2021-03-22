package com.jeissonmgz.kardex.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDetailsDto {

    ProductDto product;
    List<ProductDetailDto> details;
}
