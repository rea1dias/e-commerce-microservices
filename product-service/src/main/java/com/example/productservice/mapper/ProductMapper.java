package com.example.productservice.mapper;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto dto);
    ProductDto toDto(Product entity);
}
