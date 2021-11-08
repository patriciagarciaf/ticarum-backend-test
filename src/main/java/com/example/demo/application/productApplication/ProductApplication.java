package com.example.demo.application.productApplication;

import java.util.UUID;

import com.example.demo.domain.productDomain.Product;

import java.util.List;


public interface ProductApplication {
    
    public ProductDTO add(CreateOrUpdateProductDTO dto);
    public ProductDTO get(UUID id);
    public ProductDTO update(UUID id, CreateOrUpdateProductDTO dto);
    public void delete(UUID id);
    public List<Product> getAll(String name,  int page, int size);
    //TODO: cambiar a projection
}
