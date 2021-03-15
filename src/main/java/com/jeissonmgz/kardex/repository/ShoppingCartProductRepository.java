package com.jeissonmgz.kardex.repository;

import com.jeissonmgz.kardex.entity.ShoppingCartKey;
import com.jeissonmgz.kardex.entity.ShoppingCartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProductEntity, ShoppingCartKey> {
}
