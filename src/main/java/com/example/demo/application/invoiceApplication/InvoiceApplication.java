package com.example.demo.application.invoiceApplication;

import java.util.UUID;

public interface InvoiceApplication {
    
    public InvoiceDTO add(CreateOrUpdateInvoiceDTO dto);
    public InvoiceDTO get(UUID id);
}
