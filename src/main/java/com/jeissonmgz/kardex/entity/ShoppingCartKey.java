package com.jeissonmgz.kardex.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
public class ShoppingCartKey {

    @Column(name = "stock_id")
    Long stockId;

    @Column(name = "product_id")
    Long productId;
}
