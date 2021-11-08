package com.example.demo.application.invoiceApplication;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
public @Getter @Setter class CreateOrUpdateInvoiceDTO {

    @NotBlank
    public String NIF;

    @NotEmpty
    public Set<UUID> products;
}
