package com.example.demo.domain.invoiceDomain;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

public interface InvoiceWriteRepository extends FindById<Invoice, UUID>, ExistsByField{

    public void add(Invoice invoice);
}
