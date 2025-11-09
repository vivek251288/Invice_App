package com.javaCoder.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	    private String invoiceNumber;
	    private String customerName;
	    private Dealer dealer;
	    private Vehicle vehicle;
	    private Double tax;
	    private Double totalAmount;
	    private String transactionId;
	    private String timestamp;
	}
