package com.example.productservice.service;

import com.example.productservice.model.ProductDto;

public interface ProductService {
    ProductDto create(ProductDto dto);
    ProductDto getById(Long id);
    ProductDto update(Long id, ProductDto dto);
    void delete(Long id);
 }
