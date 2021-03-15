package com.jeissonmgz.kardex.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity(name="stock_product")
public class ShoppingCartProductEntity {

    @EmbeddedId
    private ShoppingCartKey stockProductEntityKey;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private StockEntity stockEntity;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    private Integer quantity;
    private long price;
}
