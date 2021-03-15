package com.jeissonmgz.kardex.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Builder
@Entity(name="shopping_car")
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private UserEntity user;
    @OneToMany
    private List<ShoppingCartProductEntity> products;
}
