package com.example.demo.domain.productDomain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
public @NoArgsConstructor @Getter @Setter class Product extends EntityBase{

    @NotBlank
    @Column(nullable = false, unique = true)
    private String description;

    @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal priceWithoutIVA;

    @Override
    public String toString() {
        return String.format("Product {id: %s, id: %s, description: %s, price without IVA: %s}", this.getId(), this.getDescription(), this.getPriceWithoutIVA());
    }
}