package com.jeissonmgz.kardex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
