package com.javaCoder.controller;






import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.javaCoder.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateInvoice(
            @RequestParam Long dealerId,
            @RequestParam Long vehicleId,
            @RequestParam String customerName) {

        byte[] pdf = invoiceService.generateInvoice(dealerId, vehicleId, customerName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
