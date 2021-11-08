package com.example.demo.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.invoiceApplication.CreateOrUpdateInvoiceDTO;
import com.example.demo.application.invoiceApplication.InvoiceApplication;
import com.example.demo.application.invoiceApplication.InvoiceDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceApplication invoiceApplication;

    @Autowired
    public InvoiceController(InvoiceApplication invoiceApplication){
        this.invoiceApplication = invoiceApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody final CreateOrUpdateInvoiceDTO dto){
        InvoiceDTO invoiceDTO = this.invoiceApplication.add(dto);

        return ResponseEntity.status(201).body(invoiceDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody CreateOrUpdateInvoiceDTO dto) {
        InvoiceDTO invoiceDTO = this.invoiceApplication.update(id, dto);
        return ResponseEntity.ok(invoiceDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        InvoiceDTO invoiceDTO = this.invoiceApplication.get(id);
        return ResponseEntity.ok(invoiceDTO);
    }
}
