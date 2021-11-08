package com.example.demo.domain.invoiceDomain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;
import com.example.demo.domain.productDomain.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
public @NoArgsConstructor @AllArgsConstructor @Getter @Setter class Invoice extends EntityBase {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String NIF;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal priceWithoutIVA;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal priceWithIVA;

    @NotNull
    @Column(nullable = false)
    private int numberOfProducts;

    @ManyToMany
    @JoinTable(name = "invoiceProduct", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    private final Set<Product> products = new HashSet<Product>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public BigDecimal calculatePriceWithoutIVA() {

        BigDecimal totalPriceWithoutIVA = new BigDecimal(0.00);

        for (Product product : products) {
            totalPriceWithoutIVA = totalPriceWithoutIVA.add(product.getPriceWithoutIVA());
        }
        totalPriceWithoutIVA = totalPriceWithoutIVA.setScale(2, RoundingMode.HALF_EVEN);
        return totalPriceWithoutIVA;
    }

    public BigDecimal calculatePriceWithIVA(){

        BigDecimal totalPriceWithIVA = new BigDecimal(0.00);
        BigDecimal IVA = new BigDecimal(1.21);

        totalPriceWithIVA = IVA.multiply(calculatePriceWithoutIVA());
        totalPriceWithIVA = totalPriceWithIVA.setScale(2, RoundingMode.HALF_EVEN);

        return totalPriceWithIVA;
    }

    @Override
    public String toString() {
        return String.format("Invoice {id: %s, NIF: %s, price without IVA: %s, price with IVA: %s, with products:[%s]}", this.getId(), this.getNIF(),
                this.getPriceWithoutIVA(), this.getPriceWithIVA(), this.getProducts().toString());
    }
}
