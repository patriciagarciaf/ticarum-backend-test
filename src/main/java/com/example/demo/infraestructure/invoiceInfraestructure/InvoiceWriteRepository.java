package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;
import com.example.demo.domain.invoiceDomain.Invoice;

public interface InvoiceWriteRepository extends FindById<Invoice, UUID>, ExistsByField{

    public void add(Invoice invoice);
    public void update(Invoice invoice);
    public void delete(Invoice invoice);
    // public void count();
    public void consolidate(Invoice invoice);
}
