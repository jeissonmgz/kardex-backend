package com.jeissonmgz.kardex.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private long price;
    private Integer max;
    private Integer min;
    private Integer quantity;

}
