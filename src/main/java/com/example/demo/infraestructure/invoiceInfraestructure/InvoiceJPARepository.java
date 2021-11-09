package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.invoiceDomain.Invoice;
import com.example.demo.domain.invoiceDomain.InvoiceProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceJPARepository extends JpaRepository<Invoice, UUID> {

  @Query(value="SELECT invoice.id FROM invoice", nativeQuery=true) // WHERE (:nif is NULL OR nif LIKE %:nif%)")
  List<InvoiceProjection> findByCriteria(@Param("nif") String NIF, Pageable pageable);
}
