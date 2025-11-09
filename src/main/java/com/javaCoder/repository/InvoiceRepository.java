package com.javaCoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	  Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
}
