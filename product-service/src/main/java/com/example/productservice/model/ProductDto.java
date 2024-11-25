package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
}
