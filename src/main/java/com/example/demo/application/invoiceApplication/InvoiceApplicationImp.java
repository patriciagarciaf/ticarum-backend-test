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
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceApplicationImp extends ApplicationBase<Invoice, UUID> implements InvoiceApplication {

    private final InvoiceWriteRepository invoiceWriteRepository;
    private final InvoiceReadRepository invoiceReadRepository;
    private final ProductApplicationImp productApplicationImp;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public InvoiceApplicationImp(final InvoiceWriteRepository invoiceWriteRepository,
            final InvoiceReadRepository invoiceReadRepository,
            final ProductApplicationImp productApplicationImp, final Logger logger) {

        super((id) -> invoiceWriteRepository.findById(id));

        this.invoiceWriteRepository = invoiceWriteRepository;
        this.invoiceReadRepository=invoiceReadRepository;
        this.productApplicationImp = productApplicationImp;
        this.logger = logger;
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
        logger.info(this.serializeObject(invoice, "added"));

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
        logger.info(this.serializeObject(invoice, "updated"));

        return this.modelMapper.map(invoice, InvoiceDTO.class);
    }

    @Override
    public void delete(UUID id) {
        Invoice invoice = this.findById(id);
        this.invoiceWriteRepository.delete(invoice);
        logger.info(this.serializeObject(invoice, "deleted"));        
    }

    @Override
    public List<InvoiceProjection> getAll(String name, int page, int size) {
        return this.invoiceReadRepository.getAll(name, page, size);
    }
}