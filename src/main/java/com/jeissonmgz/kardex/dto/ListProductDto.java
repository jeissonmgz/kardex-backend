package com.jeissonmgz.kardex.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListProductDto {
    private Integer idUser;
    private List<ProductDto> products;
}
