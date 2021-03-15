package com.jeissonmgz.kardex.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
public class ShoppingCartKey implements Serializable {

    @Column(name = "stock_id")
    Long stockId;

    @Column(name = "product_id")
    Long productId;
}
