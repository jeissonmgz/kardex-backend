package com.jeissonmgz.kardex.dto;

import com.jeissonmgz.kardex.entity.EConcept;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDetailDto {
    private Date date;
    private EConcept concept;
    private boolean isInput;
    private Integer quantity;
    private BigDecimal value;
    private Integer quantityTotal;
    private BigDecimal valueTotal;

}
