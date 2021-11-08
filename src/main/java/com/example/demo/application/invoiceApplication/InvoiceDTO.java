package com.example.demo.application.invoiceApplication;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.productDomain.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class InvoiceDTO {

    private UUID id;
    
    private String name;

    private UUID image;

    private BigDecimal price;

    private Set<Product> Ingredients = new HashSet<Product>();
}
