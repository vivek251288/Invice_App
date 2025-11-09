package com.javaCoder.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.io.exceptions.IOException;
import com.javaCoder.model.Dealer;
import com.javaCoder.model.Invoice;
import com.javaCoder.model.Vehicle;
import com.javaCoder.repository.DealerRepository;
import com.javaCoder.repository.InvoiceRepository;
import com.javaCoder.repository.VehicleRepository;
import com.javaCoder.utility.PdfGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final DealerRepository dealerRepository;
    private final VehicleRepository vehicleRepository;
    private final InvoiceRepository invoiceRepository;
    private final PdfGenerator pdfGenerator;

    // Path where invoices will be saved (you can configure this in
    // application.properties)
    @Value("${invoice.storage.path:invoices/}")
    private String invoiceStoragePath;

    public String generateInvoice(Long dealerId, Long vehicleId, String customerName) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new RuntimeException("Dealer not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        String invoiceNumber = "INV-" + System.currentTimeMillis();
        String transactionId = UUID.randomUUID().toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        double tax = vehicle.getPrice() * 0.10;
        double total = vehicle.getPrice() + tax;

        // 1️⃣ Create Invoice object
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setCustomerName(customerName);
        invoice.setDealer(dealer);
        invoice.setVehicle(vehicle);
        invoice.setTax(tax);
        invoice.setTotalAmount(total);
        invoice.setTransactionId(transactionId);
        invoice.setTimestamp(timestamp);

        // 2️⃣ Generate PDF
        byte[] pdfBytes = pdfGenerator.generatePdf(invoice);

        // 3️⃣ Save PDF to file system
        try {
            File directory = new File(invoiceStoragePath);
            if (!directory.exists())
                directory.mkdirs();

            String fileName = invoiceNumber + ".pdf";
            File pdfFile = new File(directory, fileName);

            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
            }

            // 4️⃣ Store file path or URL
            String fileUrl = "/files/" + fileName; // assuming you’ll expose this via static resource mapping
            invoice.setPdfUrl(fileUrl);

            // 5️⃣ Save invoice in DB
            invoiceRepository.save(invoice);

            return fileUrl;

        } catch (IOException e) {
            throw new RuntimeException("Error saving invoice PDF", e);

        } catch (Exception e) {
            throw new RuntimeException("Unknown Error saving invoice", e);
        }

    }
}
