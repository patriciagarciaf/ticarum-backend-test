package com.example.demo.application.productApplication;

import java.util.UUID;

import com.example.demo.domain.productDomain.ProductProjection;

import java.util.List;


public interface ProductApplication {

    public ProductDTO get(UUID id);
    public List<ProductProjection> getAll(String name,  int page, int size);
}
