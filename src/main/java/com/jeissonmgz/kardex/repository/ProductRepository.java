package com.jeissonmgz.kardex.repository;

import com.jeissonmgz.kardex.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
