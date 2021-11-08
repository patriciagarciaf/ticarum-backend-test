package com.example.demo.infrastructure.productInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.productDomain.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJPARepository extends JpaRepository<Product, UUID>{

    @Query("SELECT p.id as id, p.description as description, p.priceWithoutIVA as priceWithoutIVA FROM Products p WHERE (:id is NULL OR id LIKE %:id%)")
    List<Product> findByCriteria(@Param("id") String id, Pageable pageable);
    // TODO: cambiar a productProyection

    @Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM products p WHERE p.id = :id")
    boolean exists(@Param("id") String id);
}
