package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.invoiceDomain.Invoice;
import com.example.demo.domain.invoiceDomain.InvoiceWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepositoryImp implements InvoiceWriteRepository {

    private InvoiceJPARepository invoiceJPARepository;

    @Autowired
    public InvoiceRepositoryImp(InvoiceJPARepository invoiceJPARepository){
        this.invoiceJPARepository = invoiceJPARepository;
    }

    @Override
    public void add(Invoice invoice) {
        this.invoiceJPARepository.save(invoice);
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        return this.invoiceJPARepository.findById(id);
    }

    @Override
    public boolean exists(String NIF) {
        return this.invoiceJPARepository.exists(NIF);
    }
}