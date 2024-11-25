package com.example.productservice.service.impl;

import com.example.productservice.entity.Product;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.ProductDto;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = mapper.toEntity(dto);
        Product savedProduct = repository.save(product);
        return mapper.toDto(savedProduct);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapper.toDto(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        Product existingProduct = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        Product updatedProduct = mapper.toEntity(dto);
        updatedProduct.setId(existingProduct.getId());
        repository.save(updatedProduct);
        return mapper.toDto(existingProduct);
    }

    @Override
    public void delete(Long id) {
        Product existingProduct = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        repository.delete(existingProduct);
    }
}
