package com.example.demo.application.productApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.productDomain.Product;
import com.example.demo.domain.productDomain.ProductProjection;
import com.example.demo.infraestructure.productInfraestructure.ProductReadRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductApplicationImp extends ApplicationBase<Product, UUID> implements ProductApplication {

    private final ProductReadRepository productReadRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public ProductApplicationImp(
            final ProductReadRepository productReadRepository, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> productReadRepository.findById(id));

        this.productReadRepository = productReadRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public ProductDTO get(UUID id) {

        Product product = this.findById(id);
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductProjection> getAll(String NIF, int page, int size) {
        return this.productReadRepository.getAll(NIF, page, size);
    }
}
