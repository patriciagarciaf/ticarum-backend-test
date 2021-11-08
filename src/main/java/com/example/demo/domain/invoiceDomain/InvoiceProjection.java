package com.example.demo.domain.invoiceDomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface InvoiceProjection {

    public UUID getId();

    public String getNIF();

    public BigDecimal getPriceWithoutIVA();

    public BigDecimal getPriceWithIVA();

    public int getNumberOfProducts();
}
