package com.javaCoder.utility;

import org.springframework.stereotype.Component;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.javaCoder.model.Invoice;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@Component
public class PdfGenerator {

    public byte[] generatePdf(Invoice invoice) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Vehicle Invoice")
                    .setFontSize(20)
                    .setBold()
                    .setMarginBottom(20));

            document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Customer Name: " + invoice.getCustomerName()));
            document.add(new Paragraph("Dealer: " + invoice.getDealer().getName()));
            document.add(new Paragraph("Dealer Address: " + invoice.getDealer().getAddress()));
            document.add(new Paragraph("Dealer Contact: " + invoice.getDealer().getContactNumber()));
            document.add(new Paragraph("Vehicle: " + invoice.getVehicle().getMake() + " " + invoice.getVehicle().getModel()));
            document.add(new Paragraph("Price: $" + invoice.getVehicle().getPrice()));
            document.add(new Paragraph("Tax (10%): $" + invoice.getTax()));
            document.add(new Paragraph("Total Amount: $" + invoice.getTotalAmount()));
            document.add(new Paragraph("Transaction ID: " + invoice.getTransactionId()));
            document.add(new Paragraph("Timestamp: " + invoice.getTimestamp()));

            // Add QR Code
            document.add(new Paragraph("\nQR Code (Transaction ID):"));
            Image qrImage = new Image(ImageDataFactory.create(generateQrCode(invoice.getTransactionId())));
            qrImage.setWidth(100).setHeight(100);
            document.add(qrImage);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF: " + e.getMessage());
        }
    }

    private byte[] generateQrCode(String text) throws Exception {
        BitMatrix matrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.QR_CODE, 150, 150);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return baos.toByteArray();
    }
}
