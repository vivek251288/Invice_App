package com.javaCoder.controller;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.javaCoder.model.Invoice;
import com.javaCoder.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * Generate an invoice, upload/save the PDF, and store the file link in DB.
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generateInvoice(
            @RequestParam Long dealerId,
            @RequestParam Long vehicleId,
            @RequestParam String customerName) {

        String pdf = invoiceService.generateInvoice(dealerId, vehicleId, customerName);

        ResponseEntity<String> response = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.TEXT_PLAIN)
                .body(pdf);

        return response;
    }

    /**
     * Download invoice PDF file by invoice number.
     * Example: /api/invoice/download?invoiceNumber=INV-1731145354000
     */
    @GetMapping("/findByInvoiceNumber")
    public ResponseEntity<?> downloadInvoice(@RequestParam String invoiceNumber) {
        return invoiceService.findByInvoiceNumber(invoiceNumber);
    }

    @GetMapping("/findByInvoiceIdAndVehicleId")
    public ResponseEntity<?> downloadInvoiceByDealerId(@RequestParam Long dealerId, @RequestParam Long vehicleId) {
        return invoiceService.findByDealerIdAndVehicleId(dealerId, vehicleId);
    }

}
