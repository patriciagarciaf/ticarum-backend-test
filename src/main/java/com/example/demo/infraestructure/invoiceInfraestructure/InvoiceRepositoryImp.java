package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.invoiceDomain.Invoice;
import com.example.demo.domain.invoiceDomain.InvoiceProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceRepositoryImp implements InvoiceWriteRepository, InvoiceReadRepository {

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

    @Override
    public void update(Invoice invoice) {
        this.invoiceJPARepository.save(invoice);
        
    }

    @Override
    public void delete(Invoice invoice) {
        this.invoiceJPARepository.delete(invoice);
    }

    @Override
    public List<InvoiceProjection> getAll(String NIF, int page, int size) {
        return this.invoiceJPARepository.findByCriteria(NIF, PageRequest.of(page, size));
    }
}