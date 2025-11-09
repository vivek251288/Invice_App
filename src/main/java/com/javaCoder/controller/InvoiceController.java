package com.javaCoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.javaCoder.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

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

    @GetMapping("/downlad")
    public String downloadInvoice(@RequestParam String invoiceNumber) {
        return new String("Invoice Downloaded");
    }

}
