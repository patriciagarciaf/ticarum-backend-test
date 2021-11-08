package com.example.demo.infrastructure.invoiceInfraestructure;

import java.util.UUID;

import com.example.demo.domain.invoiceDomain.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceJPARepository extends JpaRepository<Invoice, UUID> {

  Invoice findByName(@Param("nif") String NIF);

  @Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM invoice i WHERE i.nif = :nif")
    boolean exists(@Param("nif") String NIF);
}
