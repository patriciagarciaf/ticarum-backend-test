package com.example.demo.domain.productDomain;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

public interface ProductReadRepository extends FindById<Product, UUID>, ExistsByField{
    
    public List<ProductProjection> getAll(String id, int page, int size);
}
