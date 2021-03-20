package com.jeissonmgz.kardex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Date date;

    @ManyToOne
    private ProductEntity product;

    @Enumerated(EnumType.STRING)
    private EConcept concept;

    private boolean isInput;
    private Integer quantity;
    private BigDecimal value;
    private Integer quantityTotal;
    private BigDecimal valueTotal;
}
