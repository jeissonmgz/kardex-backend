package com.jeissonmgz.kardex.service;

import com.jeissonmgz.kardex.dto.ProductDetailDto;
import com.jeissonmgz.kardex.dto.ProductDetailsDto;
import com.jeissonmgz.kardex.dto.ProductDto;
import com.jeissonmgz.kardex.entity.ProductEntity;
import com.jeissonmgz.kardex.exception.BusinessException;
import com.jeissonmgz.kardex.exception.BusinessExceptionMessage;
import com.jeissonmgz.kardex.repository.ProductRepository;
import com.jeissonmgz.kardex.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public List<ProductDto> getProducts(){
        return productRepository.findAll().stream().map(productEntity ->
                        ProductDto.builder()
                                .id(productEntity.getId())
                                .name(productEntity.getName())
                                .price(productEntity.getPrice())
                                .build()
                ).collect(Collectors.toList());
    }

    private ProductDto getProduct(Integer id) throws BusinessException{
        return productRepository.findById(id).map(productEntity ->
                ProductDto.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .build()
        ).orElseThrow(()-> new BusinessException(BusinessExceptionMessage.PRODUCT_NOT_FOUND));
    }

    public ProductDetailsDto getProductDetail(ProductDto productDto) throws BusinessException {
        productDto = getProduct(productDto.getId());
        return ProductDetailsDto.builder()
                .product(productDto)
                .details(
                stockRepository.findByProduct(ProductEntity.builder().id(productDto.getId()).build())
                .stream()
                .map(
                        stockEntity -> ProductDetailDto.builder()
                                .concept(stockEntity.getConcept())
                                .date(stockEntity.getDate())
                                .isInput(stockEntity.isInput())
                                .quantity(stockEntity.getQuantity())
                                .value(stockEntity.getValue())
                                .quantityTotal(stockEntity.getQuantityTotal())
                                .valueTotal(stockEntity.getValueTotal())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .max(productDto.getMax())
                .min(productDto.getMin())
                .price(productDto.getPrice())
                .build());
        productDto.setId(productEntity.getId());
        return productDto;
    }
}
