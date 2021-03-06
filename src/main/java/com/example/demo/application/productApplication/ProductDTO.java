package com.example.demo.application.productApplication;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class ProductDTO{
    
    private UUID id;
    
    private String description;

     private BigDecimal priceWithoutIVA;
}
