package com.example.demo.infraestructure.invoiceInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.invoiceDomain.Invoice;
import com.example.demo.domain.invoiceDomain.InvoiceProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceJPARepository extends JpaRepository<Invoice, UUID> {

  Invoice findByName(@Param("nif") String NIF);

  @Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM invoice i WHERE i.nif = :nif")
    boolean exists(@Param("nif") String NIF);

  @Query("SELECT i.id as id, i.NIF as NIF, i.priceWithoutIVA as priceWithoutIVA FROM invoice i WHERE (:NIF is NULL OR name LIKE %:NIF%)")
  List<InvoiceProjection> findByCriteria(@Param("NIF") String NIF, org.springframework.data.domain.Pageable pageable);
}
