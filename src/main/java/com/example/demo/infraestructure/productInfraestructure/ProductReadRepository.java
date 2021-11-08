package com.example.demo.infraestructure.productInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.functionalInterfaces.FindById;
import com.example.demo.domain.productDomain.Product;
import com.example.demo.domain.productDomain.ProductProjection;

public interface ProductReadRepository extends FindById<Product, UUID>{
    
    public List<ProductProjection> getAll(String id, int page, int size);
}
