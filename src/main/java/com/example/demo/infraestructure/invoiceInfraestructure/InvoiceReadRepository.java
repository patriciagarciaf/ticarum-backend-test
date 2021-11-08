package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.List;

import com.example.demo.domain.invoiceDomain.InvoiceProjection;

public interface InvoiceReadRepository {
    
    public List<InvoiceProjection> getAll(String NIF, int page, int size);
}
