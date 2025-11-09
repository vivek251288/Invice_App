package com.javaCoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceRequest {
    private Long dealerId;
    private Long vehicleId;
    private String customerName;
    // getters and setters
}
