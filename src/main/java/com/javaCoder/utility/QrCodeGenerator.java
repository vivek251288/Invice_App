package com.javaCoder.utility;


import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;


@Component
public class QrCodeGenerator {

    public byte[] generateQrCode(String text) {
        try {
            int width = 150;
            int height = 150;
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR Code", e);
        }
    }
}
