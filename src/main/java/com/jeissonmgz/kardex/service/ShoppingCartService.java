package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.ShoppingCartEntity;
import com.jeissonmgz.kardex.entity.ShoppingCartProductEntity;
import com.jeissonmgz.kardex.entity.UserEntity;
import com.jeissonmgz.kardex.repository.ShoppingCartProductRepository;
import com.jeissonmgz.kardex.repository.ShoppingCartRepository;
import com.jeissonmgz.kardex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;
    private final UserRepository userRepository;

    public void add(String userId, ProductDto product) {
        UserEntity userEntity = this.userRepository.findByUsername(userId).get();
        ShoppingCartEntity shoppingCartEntity = getOrCreateShoppingCart(userEntity);
        ShoppingCartProductEntity shoppingCartProductEntity =
                getOrCreateShoppingCartProduct(shoppingCartEntity, product);
        shoppingCartProductEntity.setQuantity(
                Integer.sum(shoppingCartProductEntity.getQuantity(), product.getQuantity())
        );
        shoppingCartProductRepository.save(shoppingCartProductEntity);
    }

    private ShoppingCartEntity getOrCreateShoppingCart (UserEntity userEntity) {
        return shoppingCartRepository.findByUser(userEntity).orElse(
                shoppingCartRepository.save(ShoppingCartEntity.builder().user(userEntity).products(new ArrayList<>()).build())
        );
    }

    private ShoppingCartProductEntity getOrCreateShoppingCartProduct (ShoppingCartEntity shoppingCartEntity, ProductDto product) {
        return shoppingCartEntity.getProducts().stream()
                .filter(shoppingCartProduct-> shoppingCartProduct.getProductEntity().getId().equals(product.getId()))
                .findFirst()
                .orElse(ShoppingCartProductEntity.builder()
                        .productEntity(ProductEntity.builder().id(product.getId()).build())
                        .quantity(0)
                        .build());
    }

    public ListProductDto get(String userId) {
        UserEntity user = this.userRepository.findByUsername(userId).get();
        ShoppingCartEntity shoppingCartEntity = getOrCreateShoppingCart(user);
        return ListProductDto.builder()
                .idUser(user.getId())
                .products(getProducts(shoppingCartEntity))
                .build();
    }

    private List<ProductDto> getProducts(ShoppingCartEntity shoppingCartEntity) {
        return shoppingCartEntity.getProducts().stream()
        .map(shoppingCartProductEntity ->
            ProductDto.builder()
                    .id(shoppingCartProductEntity.getProductEntity().getId())
                    .name(shoppingCartProductEntity.getProductEntity().getName())
                    .quantity(shoppingCartProductEntity.getQuantity())
                    .price(shoppingCartProductEntity.getPrice())
                    .build()
        ).collect(Collectors.toList());
    }
}
