package com.example.demo.domain.productDomain;

import java.util.List;

public interface ProductReadRepository {
    
    public List<ProductProjection> getAll(String id, int page, int size);
}
