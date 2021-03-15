package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ListProductDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.dto.UserDto;
import com.jeissonmgz.kardex.entity.EConcept;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.entity.StockEntity;
import com.jeissonmgz.kardex.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ShoppingCartService shoppingCartService;

    public void input (ListProductDto listProductDto) {
        listProductDto.getProducts()
                .forEach(productDto -> {
                    stockRepository.save(
                            StockEntity.builder()
                                    .concept(EConcept.BUY)
                                    .date(new Date())
                                    .isInput(true)
                                    .quantity(productDto.getQuantity())
                                    .product(
                                            ProductEntity.builder()
                                                    .id(productDto.getId())
                                                    .build())
                                    .value(BigDecimal.valueOf(productDto.getPrice()))
                                    .quantityTotal(Integer.sum(getQuantity(productDto), productDto.getQuantity()))
                                    .valueTotal(getPriceAverage(productDto).add(
                                            BigDecimal.valueOf(productDto.getPrice() * productDto.getQuantity())
                                                    .divide(BigDecimal.valueOf(productDto.getQuantity())))
                                    )
                                    .build()
                    );
                });
    }

    private Integer getQuantity(ProductDto productDto) {
        List<StockEntity> productEntityList = stockRepository.findByProduct(ProductEntity.builder()
                .id(productDto.getId())
                .build());
        Integer inputs = filterStockQuantityByIsInput(productEntityList, true);
        Integer outputs = filterStockQuantityByIsInput(productEntityList, false);
        return inputs - outputs;
    }

    private Integer filterStockQuantityByIsInput(List<StockEntity> productEntityList, boolean isInput) {
        return productEntityList.stream()
                .filter(stockEntity -> stockEntity.isInput() == isInput)
                .map(StockEntity::getQuantity)
                .reduce(0, Integer::sum);
    }

    private BigDecimal filterStockTotalPriceByIsInput(List<StockEntity> productEntityList, boolean isInput) {
        return productEntityList.stream()
                .filter(stockEntity -> stockEntity.isInput() == isInput)
                .map(stockEntity->
                                stockEntity.getValue().multiply(
                                        BigDecimal.valueOf(stockEntity.getQuantity())))
                .reduce(BigDecimal.ZERO, (value, total)-> value.add(total));
    }

    private BigDecimal getPriceAverage(ProductDto productDto) {
        List<StockEntity> productEntityList = stockRepository.findByProduct(ProductEntity.builder()
                .id(productDto.getId())
                .build());
        Integer inputsQuantity = filterStockQuantityByIsInput(productEntityList, true);
        BigDecimal inputsPriceTotal = filterStockTotalPriceByIsInput(productEntityList, true);
        return inputsPriceTotal.divide(BigDecimal.valueOf(inputsQuantity));
    }

    public boolean output(UserDto user) {
        List<ProductDto> products = shoppingCartService.get(user).getProducts();
        for (ProductDto productDto : products) {
            if (getQuantity(productDto) < productDto.getQuantity() )
                return false;
        }
        products.forEach(productDto -> {
                    stockRepository.save(
                            StockEntity.builder()
                                    .concept(EConcept.SELL)
                                    .date(new Date())
                                    .isInput(false)
                                    .quantity(productDto.getQuantity())
                                    .product(
                                            ProductEntity.builder()
                                                    .id(productDto.getId())
                                                    .build())
                                    .value(BigDecimal.valueOf(productDto.getPrice()))
                                    .quantityTotal((getQuantity(productDto) - productDto.getQuantity()))
                                    .valueTotal(getPriceAverage(productDto))
                                    .build()
                    );
                });
        return true;
    }
}
