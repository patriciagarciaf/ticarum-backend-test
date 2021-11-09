package com.example.demo.infraestructure.productInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.productDomain.Product;
import com.example.demo.domain.productDomain.ProductProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJPARepository extends JpaRepository<Product, UUID>{

    @Query("SELECT products.id FROM product p WHERE (:id is NULL OR id LIKE %:id%)")
    List<ProductProjection> findByCriteria(@Param("id") String id, Pageable pageable);
}
