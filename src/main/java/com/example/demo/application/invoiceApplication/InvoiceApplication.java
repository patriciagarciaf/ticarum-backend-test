package com.example.demo.application.invoiceApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.invoiceDomain.InvoiceProjection;

public interface InvoiceApplication {
    
    public InvoiceDTO add(CreateOrUpdateInvoiceDTO dto);
    public void consolidate(UUID id);
    public InvoiceDTO get(UUID id);
    public InvoiceDTO update(UUID id, CreateOrUpdateInvoiceDTO dto);
    public void delete(UUID id);
    public List<InvoiceProjection> getAll(String name,  int page, int size);
}
