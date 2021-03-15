package com.jeissonmgz.kardex.repository;

import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

    List<StockEntity> findByProduct(ProductEntity productEntity);

}
