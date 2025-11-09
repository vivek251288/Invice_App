package com.javaCoder.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaCoder.model.Dealer;
import com.javaCoder.model.Invoice;
import com.javaCoder.model.Vehicle;
import com.javaCoder.repository.DealerRepository;
import com.javaCoder.repository.VehicleRepository;
import com.javaCoder.utility.PdfGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final DealerRepository dealerRepository;
    private final VehicleRepository vehicleRepository;
    private final PdfGenerator pdfGenerator;

    public byte[] generateInvoice(Long dealerId, Long vehicleId, String customerName) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new RuntimeException("Dealer not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        String invoiceNumber = "INV-" + System.currentTimeMillis();
        String transactionId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        double tax = vehicle.getPrice() * 0.10;
        double total = vehicle.getPrice() + tax;

        Invoice invoice = new Invoice(invoiceNumber, customerName, dealer, vehicle, tax, total, transactionId, timestamp);

        return pdfGenerator.generatePdf(invoice);
	
    }
}
