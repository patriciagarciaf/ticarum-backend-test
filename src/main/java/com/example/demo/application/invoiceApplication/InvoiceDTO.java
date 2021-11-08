package com.example.demo.application.invoiceApplication;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class InvoiceDTO {
    
    private String NIF;

    private BigDecimal priceWithoutIVA;

    private BigDecimal priceWithIVA;

    private int numberOfProducts;

    private Set<UUID> products = new HashSet<UUID>();
}
