package com.jeissonmgz.kardex.repository;

import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.ShoppingCartEntity;
import com.jeissonmgz.kardex.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {

    Optional<ShoppingCartEntity> findByUser(UserEntity user);
}
