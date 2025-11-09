package com.javaCoder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.Dealer;
import com.javaCoder.model.Invoice;
import com.javaCoder.model.Vehicle;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    List<Invoice> findByDealerAndInvoiceNumber(Dealer dealer, String invoiceNumber);

    List<Invoice> findByDealerAndVehicle(Dealer dealer, Vehicle vehicle);

    // You can also support IDs directly if you want:
    List<Invoice> findByDealerIdAndVehicleId(Long dealerId, Long vehicleId);
}
