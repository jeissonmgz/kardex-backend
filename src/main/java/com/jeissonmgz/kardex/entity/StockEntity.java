package com.jeissonmgz.kardex.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Entity(name="stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Date date;

    @ManyToOne
    private ProductEntity productEntity;

    @Enumerated(EnumType.STRING)
    private EConcept concept;

    private boolean isInput;
    private Integer quantity;
    private BigDecimal value;
    private Integer quantityTotal;
    private BigDecimal valueTotal;
}
