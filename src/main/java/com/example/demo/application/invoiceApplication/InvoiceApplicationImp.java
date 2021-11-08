package com.example.demo.application.invoiceApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.application.productApplication.ProductApplicationImp;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.invoiceDomain.Invoice;
import com.example.demo.domain.invoiceDomain.InvoiceProjection;
import com.example.demo.domain.productDomain.Product;
import com.example.demo.infraestructure.invoiceInfraestructure.InvoiceReadRepository;
import com.example.demo.infraestructure.invoiceInfraestructure.InvoiceWriteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceApplicationImp extends ApplicationBase<Invoice, UUID> implements InvoiceApplication {

    private final InvoiceWriteRepository invoiceWriteRepository;
    private final InvoiceReadRepository invoiceReadRepository;
    private final ProductApplicationImp productApplicationImp;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public InvoiceApplicationImp(final InvoiceWriteRepository invoiceWriteRepository,
            final InvoiceReadRepository invoiceReadRepository,
            final ProductApplicationImp productApplicationImp) {

        super((id) -> invoiceWriteRepository.findById(id));

        this.invoiceWriteRepository = invoiceWriteRepository;
        this.invoiceReadRepository=invoiceReadRepository;
        this.productApplicationImp = productApplicationImp;
    }

    @Override
    public InvoiceDTO add(CreateOrUpdateInvoiceDTO dto) {

        Invoice invoice = this.modelMapper.map(dto, Invoice.class);
        invoice.setId(UUID.randomUUID());
        for (UUID productId : dto.getProducts()) {
            Product product = this.modelMapper.map(productApplicationImp.get(productId), Product.class);
            invoice.addProduct(product);
        }
        invoice.setPriceWithIVA(invoice.calculatePriceWithIVA());
        invoice.setPriceWithoutIVA(invoice.calculatePriceWithoutIVA());
        this.invoiceWriteRepository.add(invoice);
        return this.modelMapper.map(invoice, InvoiceDTO.class);
    }

    @Override
    public InvoiceDTO get(UUID id) {
        Invoice invoice = this.findById(id);
        return this.modelMapper.map(invoice, InvoiceDTO.class);
    }

    @Override
    public InvoiceDTO update(UUID id, CreateOrUpdateInvoiceDTO dto) {
        Invoice invoice = this.findById(id);
        this.modelMapper.map(dto, invoice);
        this.invoiceWriteRepository.update(invoice);
        return this.modelMapper.map(invoice, InvoiceDTO.class);
    }

    @Override
    public void delete(UUID id) {
        Invoice invoice = this.findById(id);
        this.invoiceWriteRepository.delete(invoice);
    }

    @Override
    public List<InvoiceProjection> getAll(String name, int page, int size) {
        return this.invoiceReadRepository.getAll(name, page, size);
    }

    public void consolidate(UUID id){
        Invoice invoice = this.findById(id);
        this.invoiceWriteRepository.consolidate(invoice);
    }
}