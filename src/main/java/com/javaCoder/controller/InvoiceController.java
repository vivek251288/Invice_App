package com.javaCoder.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.javaCoder.dto.InvoiceRequest;
import com.javaCoder.model.Invoice;
import com.javaCoder.repository.InvoiceRepository;
import com.javaCoder.service.InvoiceService;
import com.javaCoder.utility.PdfGenerator;


//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
   // private final InvoiceService invoiceService;
    private final InvoiceRepository invoiceRepository;
    private final PdfGenerator pdfGenerator;

    @Value("${invoice.storage.path}")
    private String invoiceStoragePath;

 //   @PostMapping("/generate")
//    public ResponseEntity<String> generateInvoice(
//            @RequestParam Long dealerId,
//            @RequestParam Long vehicleId,
//            @RequestParam String customerName) {
//
//        String pdf = invoiceService.generateInvoice(dealerId, vehicleId, customerName);
    
    // @PostMapping("/generate")
    // public ResponseEntity<String> generateInvoice(@RequestBody InvoiceRequest request) {
    //     String pdf = invoiceService.generateInvoice(
    //         request.getDealerId(),
    //         request.getVehicleId(),
    //         request.getCustomerName()
    //     );
    @PostMapping("/generate/{dealerId}/{vehicleId}/{customerName}")
    public ResponseEntity<String> generateInvoice(
            @PathVariable Long dealerId,
            @PathVariable Long vehicleId,
            @PathVariable String customerName) {

        String pdf = invoiceService.generateInvoice(dealerId, vehicleId, customerName);



        ResponseEntity<String> response = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.TEXT_PLAIN)
                .body(pdf);

        return response;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadInvoice(@RequestParam String invoiceNumber) throws IOException {
        // Correctly resolve path
        File directory = new File(invoiceStoragePath);
        if (!directory.exists()) {
            directory.mkdirs(); // ✅ ensure folder exists
        }

        File file = new File(directory, invoiceNumber + ".pdf");
        System.out.println("🔍 Looking for file at: " + file.getAbsolutePath());

        // ✅ If missing, regenerate it from DB
        if (!file.exists()) {
            System.out.println("⚠️ File not found, regenerating PDF...");
            Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
                    .orElseThrow(() -> new RuntimeException("Invoice not found in DB"));

            byte[] pdfBytes = pdfGenerator.generatePdf(invoice);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(pdfBytes);
            }
            System.out.println("✅ Regenerated and saved at: " + file.getAbsolutePath());
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(resource);
    }
      @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> info = new HashMap<>();
        info.put("message", "✅ Invoice PDF Service is running successfully!");
        info.put("generate_api", "/api/invoice/generate (POST)");
        info.put("download_api", "/api/invoice/download?invoiceNumber=XXXX (GET)");
        return ResponseEntity.ok(info);
    }
}
