package com.example.demo.infraestructure.productInfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.productDomain.Product;
import com.example.demo.domain.productDomain.ProductProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImp implements ProductReadRepository {
    
    private final ProductJPARepository productJPARepository;

    @Autowired
    public ProductRepositoryImp(ProductJPARepository productJPARepository){
        this.productJPARepository = productJPARepository;
    }

    @Override
    public Optional<Product>findById(UUID id) {
        return this.productJPARepository.findById(id);
    }

    @Override
    public List<ProductProjection> getAll(String id, int page, int size) {
        return this.productJPARepository.findByCriteria(id, PageRequest.of(page, size)
        );
    }

}
