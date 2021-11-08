package com.example.demo.domain.productDomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductProjection {

    public UUID getId();

    public String getDescription();

    public BigDecimal getPriceWithoutIVA();

}
