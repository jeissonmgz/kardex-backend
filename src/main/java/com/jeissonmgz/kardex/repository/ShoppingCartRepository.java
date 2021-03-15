package com.jeissonmgz.kardex.repository;

import com.jeissonmgz.kardex.entity.ShoppingCartEntity;
import com.jeissonmgz.kardex.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Integer> {

    Optional<ShoppingCartEntity> findByUser(UserEntity user);
}
